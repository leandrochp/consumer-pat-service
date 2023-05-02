package com.github.leandrochp.consumerservice.domain.exceptions;

public class ConsumerNotFoundException extends RuntimeException {

    public ConsumerNotFoundException(String message) {
        super(message);
    }
}
