package com.github.leandrochp.consumerpatservice.domain.exceptions;

public class ConsumerNotFoundException extends RuntimeException {

    public ConsumerNotFoundException(String message) {
        super(message);
    }
}
