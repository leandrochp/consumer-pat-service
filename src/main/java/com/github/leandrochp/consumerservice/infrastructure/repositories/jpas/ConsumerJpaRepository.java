package com.github.leandrochp.consumerservice.infrastructure.repositories.jpas;

import com.github.leandrochp.consumerservice.infrastructure.repositories.entities.ConsumerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ConsumerJpaRepository extends JpaRepository<ConsumerEntity, Integer> {

    @Query("From ConsumerEntity c")
    Page<ConsumerEntity> findAllPageable(Pageable pageable);
}
