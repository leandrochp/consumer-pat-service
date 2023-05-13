package com.github.leandrochp.consumerservice.domain.entities;

import com.github.leandrochp.consumerservice.domain.enums.CardType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class ConsumerMock {

    public static Consumer sample() {
        Consumer consumer = new Consumer();
        consumer.setId(1);
        consumer.setName("John Wick");
        consumer.setDocumentNumber("123456789");
        consumer.setBirthDate(LocalDate.now());

        Contact contact = new Contact();
        contact.setMobilePhoneNumber("1234567890");
        contact.setResidencePhoneNumber("1234567890");
        contact.setWorkPhoneNumber("1234567890");
        contact.setEmail("test@mockito.com");

        Address address = new Address();
        address.setStreet("Street");
        address.setNumber(123);
        address.setCity("City");
        address.setCountry("Country");
        address.setPortalCode("123");

        Card card = new Card();
        card.setCardType(CardType.PHARMACY);
        card.setBalance(BigDecimal.ZERO);

        consumer.setContact(contact);
        consumer.setAddress(address);
        consumer.setCards(List.of(card));

        return consumer;
    }
}
