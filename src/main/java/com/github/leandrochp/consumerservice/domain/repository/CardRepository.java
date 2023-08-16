package com.github.leandrochp.consumerservice.domain.repository;

import com.github.leandrochp.consumerservice.domain.consumer.Card;

public interface CardRepository {

    Card findByCardNumber(String cardNumber);
    void updateBalance(Card card);
}
