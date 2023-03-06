package com.github.leandrochp.consumerpatservice.application.web.wrappers;

import com.github.leandrochp.consumerpatservice.application.web.requests.DebitCardRequest;
import com.github.leandrochp.consumerpatservice.domain.dto.DebitCard;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@NoArgsConstructor
public class DebitCardWrapper {

    private DebitCard debitCard;

    public DebitCardWrapper(int consumerId, DebitCardRequest debitCardRequest) {
        this.debitCard = new DebitCard();

        BeanUtils.copyProperties(debitCardRequest, debitCard);
        debitCard.setConsumerId(consumerId);
    }

    public DebitCard toModel() {
        return debitCard;
    }
}
