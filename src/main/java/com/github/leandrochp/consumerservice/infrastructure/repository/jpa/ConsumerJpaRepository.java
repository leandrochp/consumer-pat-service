package com.github.leandrochp.consumerservice.infrastructure.repository.jpa;

import com.github.leandrochp.consumerservice.infrastructure.repository.entity.ConsumerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ConsumerJpaRepository extends JpaRepository<ConsumerEntity, Integer> {

    @Query("From consumer c")
    Page<ConsumerEntity> findAllPageable(Pageable pageable);
}
