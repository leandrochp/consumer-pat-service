package com.github.leandrochp.consumerservice.application.web.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.leandrochp.consumerservice.domain.enums.CardType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class SettlementRequest {
    @JsonProperty("card_type")
    private CardType cardType;
    @JsonProperty("establishment_type")
    private Integer establishmentType;
    @JsonProperty("establishment_name")
    private String establishmentName;
    @JsonProperty("product_description")
    private String productDescription;
    @JsonProperty("card_number")
    private String cardNumber;

    private BigDecimal value;
}
