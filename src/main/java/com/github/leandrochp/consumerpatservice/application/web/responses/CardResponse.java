package com.github.leandrochp.consumerpatservice.application.web.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.leandrochp.consumerpatservice.domain.enums.EstablishmentType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class CardResponse {
    @JsonProperty("establishment_type")
    private EstablishmentType establishmentType;
    @JsonProperty("card_number")
    private String cardNumber;
    private BigDecimal value;
}
