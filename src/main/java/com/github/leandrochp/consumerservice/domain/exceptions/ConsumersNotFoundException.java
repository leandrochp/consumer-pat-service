package com.github.leandrochp.consumerservice.domain.exceptions;

public class ConsumersNotFoundException extends RuntimeException {

    public ConsumersNotFoundException(String message) {
        super(message);
    }
}
