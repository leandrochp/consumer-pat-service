package com.github.leandrochp.consumerservice.domain.enums;

import lombok.val;

import java.math.BigDecimal;

public enum CardType {
    FOOD(10.0) {
        @Override
        public BigDecimal taxCalculate(BigDecimal value) {
            val tax = value.divide(new BigDecimal(100)).multiply(percent);
            return value.subtract(tax);
        }
    },
    PHARMACY(0.0) {
        @Override
        public BigDecimal taxCalculate(BigDecimal value) {
            return value;
        }
    },
    FUEL(35.0) {
        @Override
        public BigDecimal taxCalculate(BigDecimal value) {
            val tax = value.divide(new BigDecimal(100)).multiply(percent);
            return value.add(tax);
        }
    };

    CardType(double percent) {
        this.percent = BigDecimal.valueOf(percent);
    }

    protected final BigDecimal percent;

    public abstract BigDecimal taxCalculate(BigDecimal value);
}
