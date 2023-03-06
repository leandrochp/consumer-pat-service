package com.github.leandrochp.consumerpatservice.domain.calculatecard;

public class CalculateDebitFuelCard implements CalculateCard {

    @Override
    public double calculate(double value) {
        double tax = (value / 100) * 35;
        value = value + tax;

        return value;
    }

}
