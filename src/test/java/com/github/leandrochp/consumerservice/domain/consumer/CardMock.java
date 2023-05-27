package com.github.leandrochp.consumerservice.domain.consumer;

import com.github.leandrochp.consumerservice.domain.enums.CardType;

import java.math.BigDecimal;

public class CardMock {

    public static Card sample(CardType cardType) {
        Card card = new Card();
        card.setCardType(cardType);
        card.setCardNumber("1234567890");
        card.setBalance(BigDecimal.valueOf(200));

        return card;
    }

    public static Card sample() {
        return sample(CardType.FUEL);
    }
}
