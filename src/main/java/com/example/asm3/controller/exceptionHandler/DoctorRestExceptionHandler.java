package com.example.asm3.controller.exceptionHandler;

import com.example.asm3.exception.DoctorNotFoundException;
import com.example.asm3.model.DoctorErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DoctorRestExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<DoctorErrorResponse> handleException(DoctorNotFoundException exception) {
        DoctorErrorResponse error = new DoctorErrorResponse();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exception.getMessage());
        error.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
