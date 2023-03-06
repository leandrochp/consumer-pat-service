package com.github.leandrochp.consumerpatservice.application.web.wrappers;

import com.github.leandrochp.consumerpatservice.application.web.requests.CardBalanceRequest;
import com.github.leandrochp.consumerpatservice.domain.dto.CardBalance;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@NoArgsConstructor
public class CardBalanceRequestWrapper {

    private CardBalance cardBalance;

    public CardBalanceRequestWrapper(int consumerId, CardBalanceRequest cardBalanceRequest) {
        this.cardBalance = new CardBalance();

        BeanUtils.copyProperties(cardBalanceRequest, cardBalance);
        cardBalance.setConsumerId(consumerId);
    }

    public CardBalance toModel() {
        return cardBalance;
    }

}
