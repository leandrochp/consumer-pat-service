package com.github.leandrochp.consumerpatservice.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CardBalance {
    private int consumerId;
    private String cardNumber;
    private double value;

}
