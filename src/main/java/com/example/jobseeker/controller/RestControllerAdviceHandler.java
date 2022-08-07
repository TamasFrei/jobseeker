package com.example.jobseeker.controller;

import com.example.jobseeker.exception.ClientApiKeyException;
import com.example.jobseeker.exception.InvalidClientException;
import com.example.jobseeker.exception.InvalidPositionException;
import com.example.jobseeker.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestControllerAdviceHandler {

    @ExceptionHandler({InvalidClientException.class, InvalidPositionException.class})
    public ResponseEntity<String> handleValidationException(RuntimeException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ClientApiKeyException.class)
    public ResponseEntity<String> handleClientApiKeyException(ClientApiKeyException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
