package com.github.leandrochp.consumerpatservice.application.web.requests;

import com.github.leandrochp.consumerpatservice.domain.enums.EstablishmentType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DebitCardRequest {
    @JsonProperty("establishment_type")
    private EstablishmentType establishmentType;
    @JsonProperty("establishment_name")
    private String establishmentName;
    @JsonProperty("product_description")
    private String productDescription;
    @JsonProperty("card_number")
    private String cardNumber;
    private double value;

}
