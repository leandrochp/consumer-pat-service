package com.github.leandrochp.consumerservice.infrastructure.repositories.entities;

import com.github.leandrochp.consumerservice.domain.entities.Card;
import com.github.leandrochp.consumerservice.domain.enums.EstablishmentType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Entity
@Table(name = "card")
public class CardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "establishment_type")
    private EstablishmentType establishmentType;
    @Column(name = "card_number")
    private String cardNumber;

    private BigDecimal value;

    @ManyToOne
    private ConsumerEntity consumer;

    public Card toModel() {
        Card card = new Card();
        card.setEstablishmentType(this.establishmentType);
        card.setCardNumber(this.cardNumber);
        card.setValue(this.value);

        return card;
    }

}
