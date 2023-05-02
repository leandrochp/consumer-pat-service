package com.github.leandrochp.consumerservice.application.web.requests;

import com.github.leandrochp.consumerservice.domain.enums.EstablishmentType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class CardRequest {
    @JsonProperty("establishment_type")
    private EstablishmentType establishmentType;
    @JsonProperty("card_number")
    private String cardNumber;

    private BigDecimal value;
}
