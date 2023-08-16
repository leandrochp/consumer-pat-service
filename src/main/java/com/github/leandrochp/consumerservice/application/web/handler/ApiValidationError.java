package com.github.leandrochp.consumerservice.application.web.handler;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ApiValidationError {
    private String object;
    private String message;

    public ApiValidationError(String object, String message) {
        this.object = object;
        this.message = message;
    }
}
