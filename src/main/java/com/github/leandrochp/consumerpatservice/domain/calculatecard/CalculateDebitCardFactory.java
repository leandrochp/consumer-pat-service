package com.github.leandrochp.consumerpatservice.domain.calculatecard;

import com.github.leandrochp.consumerpatservice.domain.enums.EstablishmentType;

public class CalculateDebitCardFactory implements CalculateCardFactory {

    public CalculateCard createCalculate(EstablishmentType establishmentType) {

        if (EstablishmentType.FOOD_CARD == establishmentType) {
            return new CalculateDebitFoodCard();
        } else if (EstablishmentType.DRUG_STORE ==  establishmentType) {
            return new CalculateDebitDrugstoreCard();
        } else {
            return new CalculateDebitFuelCard();
        }
    }
}
