package com.github.leandrochp.consumerpatservice.domain.dto;

import com.github.leandrochp.consumerpatservice.domain.enums.EstablishmentType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DebitCard {
    private int consumerId;
    private EstablishmentType establishmentType;
    private String establishmentName;
    private String productDescription;
    private String cardNumber;
    private double value;

}
