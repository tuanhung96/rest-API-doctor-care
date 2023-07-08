package com.example.asm3.exception;

public class ClinicNotFoundException extends RuntimeException{
    public ClinicNotFoundException(String message) {
        super(message);
    }
}
