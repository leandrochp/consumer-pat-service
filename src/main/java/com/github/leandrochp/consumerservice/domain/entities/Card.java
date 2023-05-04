package com.github.leandrochp.consumerservice.domain.entities;

import com.github.leandrochp.consumerservice.domain.enums.CardType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.val;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class Card {
    private CardType cardType;
    private String cardNumber;
    private BigDecimal value;

    public void add(BigDecimal value) {
        this.value = this.value.add(value);
    }

    public void subtract(BigDecimal value) {
        val tax = cardType.taxCalculate(value);
        this.value = this.value.subtract(tax);
    }
}
