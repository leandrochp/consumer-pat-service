package com.github.leandrochp.consumerservice.domain.exception;

public class ConsumerNotFoundException extends RuntimeException {

    public ConsumerNotFoundException(String message) {
        super(message);
    }
}
