package com.github.leandrochp.consumerservice.domain.enums;

import lombok.val;

import java.math.BigDecimal;

public enum EstablishmentType {
    FOOD {
        @Override
        public BigDecimal taxCalculate(BigDecimal value) {
            val cashback = value.divide(new BigDecimal(100)).multiply(BigDecimal.valueOf(10.0));
            return value.subtract(cashback);
        }
    },
    DRUG_STORE {
        @Override
        public BigDecimal taxCalculate(BigDecimal value) {
            return value;
        }
    },
    FUEL {
        @Override
        public BigDecimal taxCalculate(BigDecimal value) {
            val tax = value.divide(new BigDecimal(100)).multiply(BigDecimal.valueOf(35.0));
            return value.add(tax);
        }
    };

    public abstract BigDecimal taxCalculate(BigDecimal value);
}
