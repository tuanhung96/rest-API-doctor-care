package com.example.asm3.controller.exceptionHandler;

import com.example.asm3.exception.ClinicNotFoundException;
import com.example.asm3.model.ClinicErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ClinicRestExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<ClinicErrorResponse> handleException(ClinicNotFoundException exception) {
        ClinicErrorResponse error = new ClinicErrorResponse();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exception.getMessage());
        error.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
