package com.example.asm3.controller.exceptionHandler;

import com.example.asm3.exception.ScheduleNotFoundException;
import com.example.asm3.model.ScheduleErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ScheduleRestExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<ScheduleErrorResponse> handleException(ScheduleNotFoundException exception) {
        ScheduleErrorResponse error = new ScheduleErrorResponse();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exception.getMessage());
        error.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
