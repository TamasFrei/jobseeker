package com.example.jobseeker.controller;

import com.example.jobseeker.exception.InvalidClientException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestControllerAdviceHandler {

    @ExceptionHandler(InvalidClientException.class)
    public ResponseEntity<String> handleValidationException(RuntimeException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
