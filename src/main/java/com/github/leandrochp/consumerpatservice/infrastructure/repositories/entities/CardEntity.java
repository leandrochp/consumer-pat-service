package com.github.leandrochp.consumerpatservice.infrastructure.repositories.entities;

import com.github.leandrochp.consumerpatservice.domain.entities.Card;
import com.github.leandrochp.consumerpatservice.domain.enums.EstablishmentType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "card")
@NoArgsConstructor
public class CardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "establishment_type")
    private int establishmentType;
    @Column(name = "card_number")
    private String cardNumber;
    private double balance;

    @ManyToOne
    @JoinColumn(name = "consumer_id")
    private ConsumerEntity consumer;

    public Card toModel() {
        Card card = new Card();
        BeanUtils.copyProperties(this, card);
        card.setEstablishmentType(EstablishmentType.getEnum(this.establishmentType));

        return card;
    }

}
