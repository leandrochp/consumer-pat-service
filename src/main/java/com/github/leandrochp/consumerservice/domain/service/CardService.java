package com.github.leandrochp.consumerservice.domain.service;

import com.github.leandrochp.consumerservice.domain.dto.Settlement;

import java.math.BigDecimal;

public interface CardService {

    void addBalance(String cardNumber, BigDecimal value);
    void updateBalance(Settlement settlement);
}
