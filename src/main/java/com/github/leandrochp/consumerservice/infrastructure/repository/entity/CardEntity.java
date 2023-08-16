package com.github.leandrochp.consumerservice.infrastructure.repository.entity;

import com.github.leandrochp.consumerservice.domain.consumer.Card;
import com.github.leandrochp.consumerservice.domain.enums.CardType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.val;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Entity(name = "card")
public class CardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "card_type")
    private CardType cardType;
    @Column(name = "card_number")
    private String cardNumber;

    private BigDecimal balance;

    @ManyToOne
    private ConsumerEntity consumer;

    public Card toModel() {
        val card = new Card();
        card.setCardType(this.cardType);
        card.setCardNumber(this.cardNumber);
        card.setBalance(this.balance);

        return card;
    }

}
