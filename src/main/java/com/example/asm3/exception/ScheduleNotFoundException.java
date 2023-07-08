package com.example.asm3.exception;

public class ScheduleNotFoundException extends RuntimeException{
    public ScheduleNotFoundException(String message) {
        super(message);
    }
}
