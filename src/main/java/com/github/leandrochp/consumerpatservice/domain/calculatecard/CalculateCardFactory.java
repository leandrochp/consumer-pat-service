package com.github.leandrochp.consumerpatservice.domain.calculatecard;

import com.github.leandrochp.consumerpatservice.domain.enums.EstablishmentType;

public interface CalculateCardFactory {

    CalculateCard createCalculate(EstablishmentType establishmentType);
}
