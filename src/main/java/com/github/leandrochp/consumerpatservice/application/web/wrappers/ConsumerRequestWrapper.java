package com.github.leandrochp.consumerpatservice.application.web.wrappers;

import com.github.leandrochp.consumerpatservice.application.web.requests.AddressRequest;
import com.github.leandrochp.consumerpatservice.application.web.requests.CardRequest;
import com.github.leandrochp.consumerpatservice.application.web.requests.ConsumerRequest;
import com.github.leandrochp.consumerpatservice.application.web.requests.ContactRequest;
import com.github.leandrochp.consumerpatservice.domain.entities.Address;
import com.github.leandrochp.consumerpatservice.domain.entities.Card;
import com.github.leandrochp.consumerpatservice.domain.entities.Consumer;
import com.github.leandrochp.consumerpatservice.domain.entities.Contact;
import lombok.NoArgsConstructor;
import lombok.val;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class ConsumerRequestWrapper {

    private Consumer consumer;

    public ConsumerRequestWrapper(ConsumerRequest consumerRequest) {
        this.consumer = new Consumer();

        BeanUtils.copyProperties(consumerRequest, consumer);
        copyFromPropertyContact(consumerRequest.getContact());
        copyFromPropertyAddress(consumerRequest.getAddress());
        copyFromPropertyCards(consumerRequest.getCards());
    }

    public Consumer toModel() {
        return consumer;
    }

    private void copyFromPropertyContact(ContactRequest contactRequest) {
        if (contactRequest == null) {
            return;
        }

        consumer.setContact(new Contact());
        BeanUtils.copyProperties(contactRequest, consumer.getContact());
    }

    private void copyFromPropertyAddress(AddressRequest addressRequest) {
        if (addressRequest == null) {
            return;
        }

        consumer.setAddress(new Address());
        BeanUtils.copyProperties(addressRequest, consumer.getAddress());
    }

    private void copyFromPropertyCards(List<CardRequest> cardsRequest) {
        if (cardsRequest == null) {
            return;
        }
        List<Card> cards = new ArrayList<>();

        for (CardRequest cardRequest : cardsRequest) {
            if (cardRequest != null) {
                val card = new Card();
                card.setEstablishmentType(cardRequest.getEstablishmentType());
                card.setCardNumber(cardRequest.getCardNumber());
                card.setBalance(cardRequest.getValue());

                cards.add(card);
            }
        }
        consumer.setCards(cards);
    }
}
