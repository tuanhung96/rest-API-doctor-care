package com.example.asm3.controller.exceptionHandler;

import com.example.asm3.exception.SpecializationNotFoundException;
import com.example.asm3.model.SpecializationErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class SpecializationRestExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<SpecializationErrorResponse> handleException(SpecializationNotFoundException exception) {
        SpecializationErrorResponse error = new SpecializationErrorResponse();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exception.getMessage());
        error.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
