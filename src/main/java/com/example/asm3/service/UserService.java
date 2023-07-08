package com.example.asm3.service;

import com.example.asm3.entity.User;
import jakarta.mail.MessagingException;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.io.UnsupportedEncodingException;

public interface UserService extends UserDetailsService {
    User findByEmail(String email);

    boolean userExists(String email);
    
    void saveUser(User user);

    void sendVerificationEmail(User user, String siteURL) throws MessagingException, UnsupportedEncodingException;

    void sendResetPasswordEmail(User user, String siteURL) throws MessagingException, UnsupportedEncodingException;

    User findByResetPasswordCode(String code);

    User findByVerificationCode(String code);

    User findById(Integer id);
}
