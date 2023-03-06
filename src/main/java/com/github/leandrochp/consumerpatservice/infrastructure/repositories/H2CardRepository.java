package com.github.leandrochp.consumerpatservice.infrastructure.repositories;

import com.github.leandrochp.consumerpatservice.domain.entities.Card;
import com.github.leandrochp.consumerpatservice.domain.repositories.CardRepository;
import com.github.leandrochp.consumerpatservice.infrastructure.repositories.entities.CardEntity;
import com.github.leandrochp.consumerpatservice.infrastructure.repositories.jpas.CardJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class H2CardRepository implements CardRepository {

    private final CardJpaRepository cardRepository;

    @Autowired
    public H2CardRepository(final CardJpaRepository cardJpaRepository) {
        this.cardRepository = cardJpaRepository;
    }

    @Override
    public Optional<Card> findByCardNumberAndConsumerId(String cardNumber, int consumerId) {
        Optional<Card> card = Optional.empty();

        Optional<CardEntity> cardEntity = cardRepository.findByCardNumberAndConsumerId(cardNumber, consumerId);
        if (cardEntity.isPresent()) {
            card = Optional.of(cardEntity.get().toModel());
        }
        return card;
    }

    @Override
    public void updateBalance(Card card) {
        Optional<CardEntity> cardEntity = cardRepository.findById(card.getId());

        cardEntity.ifPresent(entity -> entity.setBalance(card.getBalance()));
        cardRepository.save(cardEntity.get());
    }
}
