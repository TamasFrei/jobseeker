package com.example.jobseeker.exception;

import java.util.List;

public class InvalidClientException extends RuntimeException {

    public InvalidClientException(String message) {
        super(message);
    }

    public InvalidClientException(List<String> messages) {
        super(messages.toString());
    }
}
