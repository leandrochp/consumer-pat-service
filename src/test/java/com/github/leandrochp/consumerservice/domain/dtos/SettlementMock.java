package com.github.leandrochp.consumerservice.domain.dtos;

import com.github.leandrochp.consumerservice.domain.enums.CardType;

import java.math.BigDecimal;

public class SettlementMock {

    public static Settlement sample() {
        Settlement settlement = new Settlement() ;
        settlement.setCardType(CardType.FOOD);
        settlement.setEstablishmentType(1);
        settlement.setEstablishmentName("Hotel Continual");
        settlement.setProductDescription("Sour drink");
        settlement.setCardNumber("5511951672116907");
        settlement.setValue(BigDecimal.valueOf(200));

        return settlement;
    }
}
