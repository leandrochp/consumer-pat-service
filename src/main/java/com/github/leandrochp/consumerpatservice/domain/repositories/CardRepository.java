package com.github.leandrochp.consumerpatservice.domain.repositories;

import com.github.leandrochp.consumerpatservice.domain.entities.Card;

public interface CardRepository {

    Card findByCardNumber(String cardNumber);
    void updateBalance(Card card);
}
