package com.example.asm3.controller.exceptionHandler;

import com.example.asm3.exception.InvalidUserException;
import com.example.asm3.exception.UserDisabledException;
import com.example.asm3.model.InvalidUserErrorResponse;
import com.example.asm3.model.ExpiredJwtErrorResponse;
import com.example.asm3.model.UserDisabledErrorResponse;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AuthenticateExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<ExpiredJwtErrorResponse> handleException(ExpiredJwtException exception) {
        ExpiredJwtErrorResponse error = new ExpiredJwtErrorResponse();
        error.setStatus(HttpStatus.FORBIDDEN.value());
        error.setMessage(exception.getMessage());
        error.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler
    public ResponseEntity<InvalidUserErrorResponse> handleException(InvalidUserException exception) {
        InvalidUserErrorResponse error = new InvalidUserErrorResponse();
        error.setStatus(HttpStatus.FORBIDDEN.value());
        error.setMessage(exception.getMessage());
        error.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler
    public ResponseEntity<UserDisabledErrorResponse> handleException(UserDisabledException exception) {
        UserDisabledErrorResponse error = new UserDisabledErrorResponse();
        error.setStatus(HttpStatus.FORBIDDEN.value());
        error.setMessage(exception.getMessage());
        error.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }
}
