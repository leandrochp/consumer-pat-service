package com.github.leandrochp.consumerpatservice.infrastructure.repositories;

import com.github.leandrochp.consumerpatservice.domain.entities.Card;
import com.github.leandrochp.consumerpatservice.domain.repositories.CardRepository;
import com.github.leandrochp.consumerpatservice.infrastructure.repositories.entities.CardEntity;
import com.github.leandrochp.consumerpatservice.infrastructure.repositories.jpas.CardJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class H2CardRepository implements CardRepository {

    private final CardJpaRepository cardJpaRepository;

    @Override
    public Card findByCardNumber(String cardNumber) {
        CardEntity cardEntity = cardJpaRepository.findByCardNumber(cardNumber);
        if (cardEntity != null) {
            return cardEntity.toModel();
        }
        return null;
    }

    @Override
    public void updateBalance(Card card) {
        CardEntity cardEntity = cardJpaRepository.findByCardNumber(card.getCardNumber());
        if (cardEntity != null) {
            cardEntity.setValue(card.getValue());

            cardJpaRepository.save(cardEntity);
        }
    }
}
