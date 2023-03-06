package com.github.leandrochp.consumerpatservice.domain.services.impl;

import com.github.leandrochp.consumerpatservice.domain.calculatecard.CalculateDebitCardFactory;
import com.github.leandrochp.consumerpatservice.domain.dto.CardBalance;
import com.github.leandrochp.consumerpatservice.domain.dto.DebitCard;
import com.github.leandrochp.consumerpatservice.domain.entities.Extract;
import com.github.leandrochp.consumerpatservice.domain.exceptions.CardNotFoundException;
import com.github.leandrochp.consumerpatservice.domain.repositories.CardRepository;
import com.github.leandrochp.consumerpatservice.domain.services.CardService;
import com.github.leandrochp.consumerpatservice.domain.services.ExtractService;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import static com.github.leandrochp.consumerpatservice.domain.exceptions.ErrorMessages.CARD_NOT_ESTABLISHMENT_TYPE;
import static com.github.leandrochp.consumerpatservice.domain.exceptions.ErrorMessages.CARD_NOT_FOUND;

@Slf4j
@Service
public class CardServiceImpl implements CardService {

    @Autowired
    CardRepository cardRepository;

    @Autowired
    ExtractService extractService;

    @Override
    public void addBalance(CardBalance cardBalance) throws CardNotFoundException {
        val card
                = cardRepository.findByCardNumberAndConsumerId(cardBalance.getCardNumber(), cardBalance.getConsumerId());
        if (card.isEmpty()) {
            String message =
                    String.format(CARD_NOT_FOUND.message(), cardBalance.getCardNumber(), cardBalance.getConsumerId());
            log.error(message);

            throw new CardNotFoundException(message);
        }

        card.ifPresent(c -> c.setBalance(c.getBalance() + cardBalance.getValue()));
        cardRepository.updateBalance(card.get());
    }

    @Override
    public void buy(DebitCard debitCard) throws CardNotFoundException {

        val optionalCard
                = cardRepository.findByCardNumberAndConsumerId(debitCard.getCardNumber(), debitCard.getConsumerId());
        if (optionalCard.isEmpty()) {
            String message =
                    String.format(CARD_NOT_FOUND.message(), debitCard.getCardNumber(), debitCard.getConsumerId());
            log.error(message);
            throw new CardNotFoundException(message);
        }

        val card = optionalCard.get();
        if (debitCard.getEstablishmentType() != card.getEstablishmentType()) {
            String message =
                    String.format(CARD_NOT_ESTABLISHMENT_TYPE.message(), debitCard.getCardNumber());
            log.error(message);
            throw new CardNotFoundException(message);
        }

        CalculateDebitCardFactory calculateDebitCardFactory = new CalculateDebitCardFactory();
        double calculateDebitValue =
                calculateDebitCardFactory.createCalculate(debitCard.getEstablishmentType()).calculate(debitCard.getValue());

        card.setBalance(card.getBalance() - calculateDebitValue);
        cardRepository.updateBalance(card);

        extractService.save(createExtract(debitCard));
    }

    private Extract createExtract(DebitCard debitCard) {
        return new Extract(
                debitCard.getEstablishmentName(),
                debitCard.getProductDescription(),
                LocalDate.now(),
                debitCard.getCardNumber(),
                debitCard.getValue(),
                debitCard.getConsumerId()
        );
    }
}
