package com.github.leandrochp.consumerservice.domain.dto;

import com.github.leandrochp.consumerservice.domain.enums.CardType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class Settlement {
    private CardType cardType;
    private Integer establishmentType;
    private String establishmentName;
    private String productDescription;
    private String cardNumber;
    private BigDecimal value;
}
