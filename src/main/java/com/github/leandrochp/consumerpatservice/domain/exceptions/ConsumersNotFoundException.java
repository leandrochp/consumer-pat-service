package com.github.leandrochp.consumerpatservice.domain.exceptions;

public class ConsumersNotFoundException extends RuntimeException {

    public ConsumersNotFoundException(String message) {
        super(message);
    }
}
