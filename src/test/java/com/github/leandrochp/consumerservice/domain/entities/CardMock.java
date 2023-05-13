package com.github.leandrochp.consumerservice.domain.entities;

import com.github.leandrochp.consumerservice.domain.enums.CardType;

import java.math.BigDecimal;

public class CardMock {

    public static Card sample() {
        Card card = new Card();
        card.setCardType(CardType.FOOD);
        card.setCardNumber("5511951672116907");
        card.setBalance(BigDecimal.valueOf(200));

        return card;
    }
}
