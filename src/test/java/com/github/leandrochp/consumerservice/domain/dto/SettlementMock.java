package com.github.leandrochp.consumerservice.domain.dto;

import com.github.leandrochp.consumerservice.domain.enums.CardType;

import java.math.BigDecimal;

public class SettlementMock {

    public static Settlement sample(CardType cardType) {
        Settlement settlement = new Settlement() ;
        settlement.setCardType(cardType);
        settlement.setEstablishmentType(1);
        settlement.setEstablishmentName("Test");
        settlement.setProductDescription("Test");
        settlement.setCardNumber("1234567890");
        settlement.setValue(BigDecimal.valueOf(200));

        return settlement;
    }

    public static Settlement sample() {
        return sample(CardType.FUEL);
    }
}
