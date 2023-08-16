package com.github.leandrochp.consumerservice.application.web.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.leandrochp.consumerservice.domain.enums.CardType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class CardResponse {
    @JsonProperty("card_type")
    private CardType cardType;
    @JsonProperty("card_number")
    private String cardNumber;

    private BigDecimal balance;
}
