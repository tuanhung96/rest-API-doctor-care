package com.example.asm3.service;

import com.example.asm3.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.io.UnsupportedEncodingException;

public interface UserService extends UserDetailsService {
    User findByEmail(String email);

}
