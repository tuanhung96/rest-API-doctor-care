package com.example.asm3.exception;

public class UserDisabledException extends RuntimeException{
    public UserDisabledException(String message) {
        super(message);
    }
}
