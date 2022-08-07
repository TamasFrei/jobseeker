package com.example.jobseeker.exception;

import java.util.List;

public class InvalidPositionException extends RuntimeException {

    public InvalidPositionException(List<String> messages) {
        super(messages.toString());
    }
}
