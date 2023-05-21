package com.github.leandrochp.consumerservice.infrastructure.repositories.entities;

import com.github.leandrochp.consumerservice.domain.consumer.Address;
import com.github.leandrochp.consumerservice.domain.consumer.Card;
import com.github.leandrochp.consumerservice.domain.consumer.Consumer;
import com.github.leandrochp.consumerservice.domain.consumer.Contact;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@Entity(name = "consumer")
public class ConsumerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    @Column(name = "document_number")
    private String documentNumber;
    @Column(name = "birth_date")
    private LocalDate birthDate;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "consumer")
    private ContactEntity contact;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "consumer")
    private AddressEntity address;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "consumer")
    private List<CardEntity> cards;

    public ConsumerEntity copyProperties(Consumer consumer) {
        BeanUtils.copyProperties(consumer, this);
        BeanUtils.copyProperties(consumer.getContact(), this.contact);
        BeanUtils.copyProperties(consumer.getAddress(), this.address);

        return this;
    }

    public Consumer toModel() {
        Consumer consumer = new Consumer();
        consumer.setId(this.id);
        consumer.setName(this.name);
        consumer.setDocumentNumber(this.documentNumber);
        consumer.setBirthDate(this.birthDate);

        consumer.setContact(this.contact.toModel());
        consumer.setAddress(this.address.toModel());
        consumer.setCards(
                this.cards.stream().map(CardEntity::toModel).collect(Collectors.toList())
        );
        return consumer;
    }

    public static ConsumerEntity toEntity(Consumer consumer) {
        ConsumerEntity consumerEntity = new ConsumerEntity();
        consumerEntity.id = consumer.getId();
        consumerEntity.name = consumer.getName();
        consumerEntity.documentNumber = consumer.getDocumentNumber();
        consumerEntity.birthDate = consumer.getBirthDate();
        consumerEntity.setContactEntity(consumer.getContact());
        consumerEntity.setAddressEntity(consumer.getAddress());
        consumerEntity.setCardEntities(consumer.getCards());

        return consumerEntity;
    }

    private void setContactEntity(Contact contact) {
        ContactEntity contactEntity = new ContactEntity();
        contactEntity.setMobilePhoneNumber(contact.getMobilePhoneNumber());
        contactEntity.setResidencePhoneNumber(contact.getResidencePhoneNumber());
        contactEntity.setWorkPhoneNumber(contact.getWorkPhoneNumber());
        contactEntity.setEmail(contact.getEmail());
        contactEntity.setConsumer(this);

        this.contact = contactEntity;
    }

    private void setAddressEntity(Address address) {
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setStreet(address.getStreet());
        addressEntity.setNumber(address.getNumber());
        addressEntity.setCity(address.getCity());
        addressEntity.setCountry(address.getCountry());
        addressEntity.setPortalCode(address.getPortalCode());
        addressEntity.setConsumer(this);

        this.address = addressEntity;
    }

    private void setCardEntities(List<Card> cards) {
        if (cards != null) {
            List<CardEntity> cardEntities = new ArrayList<>();
            for (Card card : cards) {
                CardEntity cardEntity = new CardEntity();
                cardEntity.setCardType(card.getCardType());
                cardEntity.setCardNumber(card.getCardNumber());
                cardEntity.setBalance(card.getBalance());
                cardEntity.setConsumer(this);

                cardEntities.add(cardEntity);
            }

            this.cards = cardEntities;
        }

    }
}
