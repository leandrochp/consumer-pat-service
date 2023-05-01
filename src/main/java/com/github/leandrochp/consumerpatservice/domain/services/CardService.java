package com.github.leandrochp.consumerpatservice.domain.services;

import com.github.leandrochp.consumerpatservice.domain.dtos.Settlement;

import java.math.BigDecimal;

public interface CardService {

    void addBalance(String cardNumber, BigDecimal value);
    void updateBalance(Settlement settlement);
}
