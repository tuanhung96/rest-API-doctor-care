package com.example.asm3.exception;

public class PatientNotFoundException extends RuntimeException{
    public PatientNotFoundException(String message) {
        super(message);
    }
}
