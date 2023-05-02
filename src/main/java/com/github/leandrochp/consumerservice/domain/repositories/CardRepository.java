package com.github.leandrochp.consumerservice.domain.repositories;

import com.github.leandrochp.consumerservice.domain.entities.Card;

public interface CardRepository {

    Card findByCardNumber(String cardNumber);
    void updateBalance(Card card);
}
