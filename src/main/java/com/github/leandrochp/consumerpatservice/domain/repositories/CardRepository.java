package com.github.leandrochp.consumerpatservice.domain.repositories;

import com.github.leandrochp.consumerpatservice.domain.entities.Card;

import java.util.Optional;

public interface CardRepository {

    Optional<Card> findByCardNumberAndConsumerId(String cardNumber, int consumerId);

    void updateBalance(Card card);
}
