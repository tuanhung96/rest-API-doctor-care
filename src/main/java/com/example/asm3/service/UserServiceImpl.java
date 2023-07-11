package com.example.asm3.service;

import com.example.asm3.dao.UserRepository;
import com.example.asm3.entity.User;
import com.example.asm3.exception.UserNotFoundException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    private UserRepository userRepository;
    private JavaMailSender mailSender;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, JavaMailSender mailSender) {
        this.userRepository = userRepository;
        this.mailSender = mailSender;
    }

    @Override
    @Transactional
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    @Transactional
    public boolean userExists(String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void sendVerificationEmail(User user, String siteURL) throws MessagingException, UnsupportedEncodingException {
        String fromAddress = "tuanhung.hn96@gmail.com";
        String senderName = "Doctor Care";
        String toAddress = user.getEmail();
        String subject = "Please verify your registration";
        String verifyUrl = siteURL + "/verify?code=" + user.getVerificationCode();
        String content = "Dear " + user.getName() + ",<br>"
                + "Please click the link below to verify your registration:<br>"
                + "<h3><a href=\""+ verifyUrl + "\" target=\"_self\">VERIFY</a></h3>"
                + "Thank you,<br>"
                + "Doctor Care";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);
        helper.setText(content, true);

        mailSender.send(message);
    }

    @Override
    @Transactional
    public User findByVerificationCode(String code) {
        return userRepository.findByVerificationCode(code);
    }

    @Override
    @Transactional
    public User findById(Integer id) {
        Optional<User> result = userRepository.findById(id);
        User user = null;

        if (result.isPresent()) {
            user = result.get();
        } else {
            throw new UserNotFoundException("Did not find user id - " + id);
        }
        return user;
    }

    @Override
    public void sendResetPasswordEmail(User user, String siteURL) throws MessagingException, UnsupportedEncodingException{
        String fromAddress = "tuanhung.hn96@gmail.com";
        String senderName = "Doctor Care";
        String toAddress = user.getEmail();
        String subject = "Reset Password";
        String resetPasswordUrl = siteURL + "/resetPassword?code=" + user.getResetPasswordCode();
        String content = "Dear " + user.getName() + ",<br>"
                + "Please send a request with new password to the link below to reset your password:<br>"
                + resetPasswordUrl + "<br>"
                + "Thank you,<br>"
                + "Doctor Care";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);
        helper.setText(content, true);

        mailSender.send(message);
    }

    @Override
    @Transactional
    public User findByResetPasswordCode(String code) {
        return userRepository.findByResetPasswordCode(code);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid email or password.");
        }
        if (!user.isEnabled()) {
            throw new UsernameNotFoundException("Account has not been verified!");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                Arrays.asList(new SimpleGrantedAuthority(user.getRole().getName()))); // param number 3 must be a Collection
    }
}
