package com.github.leandrochp.consumerpatservice.domain.entities;

import com.github.leandrochp.consumerpatservice.domain.enums.EstablishmentType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Card {
    @JsonIgnore
    private int id;
    @JsonProperty("establishment_type")
    private EstablishmentType establishmentType;
    @JsonProperty("card_number")
    private String cardNumber;
    private double balance;

}
