package com.github.leandrochp.consumerservice.domain.services;

import com.github.leandrochp.consumerservice.domain.dtos.Settlement;

import java.math.BigDecimal;

public interface CardService {

    void addBalance(String cardNumber, BigDecimal value);
    void updateBalance(Settlement settlement);
}
