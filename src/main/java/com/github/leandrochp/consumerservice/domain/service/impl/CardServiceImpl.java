package com.github.leandrochp.consumerservice.domain.service.impl;

import com.github.leandrochp.consumerservice.domain.dto.Settlement;
import com.github.leandrochp.consumerservice.domain.exception.CardNotFoundException;
import com.github.leandrochp.consumerservice.domain.exception.EstablishmentTypeException;
import com.github.leandrochp.consumerservice.domain.repository.CardRepository;
import com.github.leandrochp.consumerservice.domain.service.CardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@Slf4j
@RequiredArgsConstructor
@Service
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;

    @Transactional
    @Override
    public void addBalance(String cardNumber, BigDecimal value) {
        log.info("Adding balance on card");
        val card = cardRepository.findByCardNumber(cardNumber);
        if (card == null) {
            val message = "The card number not found";
            log.error(message);

            throw new CardNotFoundException(message);
        }

        card.add(value);
        cardRepository.updateBalance(card);

    }

    @Transactional
    @Override
    public void updateBalance(Settlement settlement) {
        log.info("Updating balance on card");
        val card = cardRepository.findByCardNumber(settlement.getCardNumber());
        if (card == null) {
            val message = "The card number not found";
            log.error(message);

            throw new CardNotFoundException(message);
        }
        if (!card.getCardType().equals(settlement.getCardType())) {
            val message = "The card type does not accept this establishment type";
            log.error(message);

            throw new EstablishmentTypeException(message);
        }

        card.subtract(settlement.getValue());
        cardRepository.updateBalance(card);
    }

}
