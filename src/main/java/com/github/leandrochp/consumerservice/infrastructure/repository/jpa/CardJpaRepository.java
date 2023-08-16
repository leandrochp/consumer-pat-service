package com.github.leandrochp.consumerservice.infrastructure.repository.jpa;

import com.github.leandrochp.consumerservice.infrastructure.repository.entity.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CardJpaRepository extends JpaRepository<CardEntity, Integer> {
    @Query(
            value = "SELECT * FROM card c WHERE c.card_number = :cardNumber",
            nativeQuery = true
    )
    CardEntity findByCardNumber(@Param("cardNumber") String cardNumber);
}
