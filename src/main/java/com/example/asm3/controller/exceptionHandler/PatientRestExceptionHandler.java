package com.example.asm3.controller.exceptionHandler;

import com.example.asm3.exception.PatientNotFoundException;
import com.example.asm3.model.PatientErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PatientRestExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<PatientErrorResponse> handleException(PatientNotFoundException exception) {
        PatientErrorResponse error = new PatientErrorResponse();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exception.getMessage());
        error.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
