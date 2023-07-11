package com.example.asm3.controller;

import com.example.asm3.exception.InvalidUserException;
import com.example.asm3.exception.UserDisabledException;
import com.example.asm3.model.ResetPasswordRequest;
import com.example.asm3.model.UserDTO;
import com.example.asm3.entity.User;
import com.example.asm3.model.AuthenticationRequest;
import com.example.asm3.model.AuthenticationResponse;
import com.example.asm3.service.UserService;
import com.example.asm3.util.JwtTokenUtil;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

@RestController
public class AuthenticationController {
    private AuthenticationManager authenticationManager;
    private JwtTokenUtil jwtTokenUtil;
    private UserService userService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, UserService userService, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = userService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    private void authenticate(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new UserDisabledException("USER_DISABLED");
        } catch (BadCredentialsException e) {
            throw new InvalidUserException("INVALID_CREDENTIALS");
        }
    }

    @PostMapping("/registerUser")
    public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO, HttpServletRequest request) throws MessagingException, UnsupportedEncodingException {

        // check password
        if (!userDTO.getPassword().equals(userDTO.getRePassword())) {
            return new ResponseEntity<>("Password is not equal", HttpStatus.BAD_REQUEST);
        }

        // check the database if user already exists
        if (userService.userExists(userDTO.getEmail())) {
            return new ResponseEntity<>("Email existed", HttpStatus.BAD_REQUEST);
        }

        // convert userDTO to user
        User user = userDTO.toUser();

        // encrypt password from plaintext to Bcrypt and save to database
        String password = user.getPassword();
        user.setPassword(passwordEncoder.encode(password));
        String randomCode = UUID.randomUUID().toString();
        user.setVerificationCode(randomCode);
        user.setEnabled(false);
        userService.saveUser(user);

        String siteURL = request.getRequestURL().toString().replace(request.getServletPath(), "");
        userService.sendVerificationEmail(user, siteURL);

        return ResponseEntity.ok("Register successfully! Please check your email to verify your account.");
    }

    @GetMapping("/verify")
    public String verifyUser(@RequestParam("code") String code) {
        User user = userService.findByVerificationCode(code);
        if (user == null) {
            return "verify fail";
        } else if (user.isEnabled()) {
            return "Email has been verified";
        } else {
            user.setVerificationCode(null);
            user.setEnabled(true);
            userService.saveUser(user);
            return "verify success";
        }
    }

    @GetMapping("/forgetPassword")
    public ResponseEntity<?> forgetPassword(@RequestParam("email") String email, HttpServletRequest request) throws MessagingException, UnsupportedEncodingException {
        User user = userService.findByEmail(email);
        if (user==null) {
            return new ResponseEntity<>("Email is not found", HttpStatus.NOT_FOUND);
        }

        String randomCode = UUID.randomUUID().toString();
        user.setResetPasswordCode(randomCode);
        userService.saveUser(user);

        String siteURL = request.getRequestURL().toString().replace(request.getServletPath(), "");
        userService.sendResetPasswordEmail(user, siteURL);
        return ResponseEntity.ok("We send you a email. Please check your email");
    }

    @PostMapping("/resetPassword")
    public String resetPassword(@RequestParam("code") String code, @RequestBody ResetPasswordRequest resetPasswordRequest) {
        User user = userService.findByResetPasswordCode(code);
        if (user == null) {
            return "reset password fail";
        } else {
            if (!resetPasswordRequest.getNewPassword().equals(resetPasswordRequest.getRePassword())) {
                return "Password is not equal";
            }
            String newPassword = passwordEncoder.encode(resetPasswordRequest.getNewPassword());
            user.setPassword(newPassword);
            user.setResetPasswordCode(null);
            userService.saveUser(user);
            return "reset password success";
        }
    }
}
