package com.github.leandrochp.consumerpatservice.infrastructure.repositories.wrappers;

import com.github.leandrochp.consumerpatservice.domain.entities.Address;
import com.github.leandrochp.consumerpatservice.domain.entities.Card;
import com.github.leandrochp.consumerpatservice.domain.entities.Consumer;
import com.github.leandrochp.consumerpatservice.domain.entities.Contact;
import com.github.leandrochp.consumerpatservice.infrastructure.repositories.entities.AddressEntity;
import com.github.leandrochp.consumerpatservice.infrastructure.repositories.entities.CardEntity;
import com.github.leandrochp.consumerpatservice.infrastructure.repositories.entities.ConsumerEntity;
import com.github.leandrochp.consumerpatservice.infrastructure.repositories.entities.ContactEntity;
import lombok.NoArgsConstructor;
import lombok.val;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class ConsumerEntityWrapper {

    private ConsumerEntity consumerEntity;

    public ConsumerEntityWrapper(ConsumerEntity consumerEntity) {
        this.consumerEntity = consumerEntity;
    }

    public ConsumerEntity copyFromProperties(Consumer consumer) {
        BeanUtils.copyProperties(consumer, consumerEntity, "id");

        copyFromPropertyContact(consumer.getContact());
        copyFromPropertyAddress(consumer.getAddress());
        copyFromPropertyCards(consumer.getCards());

        return consumerEntity;
    }

    private void copyFromPropertyContact(Contact contact) {
        if (contact == null) {
            return;
        }

        if (consumerEntity.getContact() == null) {
            consumerEntity.setContact(new ContactEntity());
        }
        BeanUtils.copyProperties(contact, consumerEntity.getContact(), "id", "consumer");
        consumerEntity.getContact().setConsumer(consumerEntity);
    }

    private void copyFromPropertyAddress(Address address) {
        if (address == null) {
            return;
        }

        if (consumerEntity.getAddress() == null) {
            consumerEntity.setAddress(new AddressEntity());
        }
        BeanUtils.copyProperties(address, consumerEntity.getAddress(), "id", "consumer");
        consumerEntity.getAddress().setConsumer(consumerEntity);
    }

    private void copyFromPropertyCards(List<Card> cards) {
        if (cards == null) {
            return;
        }

        if (consumerEntity.getCards() == null) {
            consumerEntity.setCards(new ArrayList<>());
        }
        for (Card card : cards) {
            if (card != null) {
                val cardEntity = new CardEntity();
                BeanUtils.copyProperties(card, cardEntity, "id", "consumer");
                cardEntity.setEstablishmentType(card.getEstablishmentType().getValue());
                cardEntity.setConsumer(consumerEntity);

                consumerEntity.getCards().add(cardEntity);
            }
        }
    }
}