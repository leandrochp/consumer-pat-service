package com.github.leandrochp.consumerpatservice.domain.entities;

import com.github.leandrochp.consumerpatservice.domain.enums.EstablishmentType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.val;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class Card {
    private EstablishmentType establishmentType;
    private String cardNumber;
    private BigDecimal value;

    public void add(BigDecimal value) {
        this.value = this.value.add(value);
    }

    public void subtract(BigDecimal value) {
        val tax = establishmentType.taxCalculate(value);
        this.value = this.value.subtract(tax);
    }
}
