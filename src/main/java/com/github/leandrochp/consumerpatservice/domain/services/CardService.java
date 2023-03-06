package com.github.leandrochp.consumerpatservice.domain.services;

import com.github.leandrochp.consumerpatservice.domain.dto.CardBalance;
import com.github.leandrochp.consumerpatservice.domain.dto.DebitCard;
import com.github.leandrochp.consumerpatservice.domain.exceptions.CardNotFoundException;

public interface CardService {

    void addBalance(CardBalance cardBalance) throws CardNotFoundException;

    void buy(DebitCard debitCard) throws CardNotFoundException;
}
