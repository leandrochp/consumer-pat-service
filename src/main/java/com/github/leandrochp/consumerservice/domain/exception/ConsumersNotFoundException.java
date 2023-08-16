package com.github.leandrochp.consumerservice.domain.exception;

public class ConsumersNotFoundException extends RuntimeException {

    public ConsumersNotFoundException(String message) {
        super(message);
    }
}
