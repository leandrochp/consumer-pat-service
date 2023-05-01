package com.github.leandrochp.consumerpatservice.domain.dtos;

import com.github.leandrochp.consumerpatservice.domain.enums.EstablishmentType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class Settlement {
    private EstablishmentType establishmentType;
    private String establishmentName;
    private String productDescription;
    private String cardNumber;
    private BigDecimal value;
}
