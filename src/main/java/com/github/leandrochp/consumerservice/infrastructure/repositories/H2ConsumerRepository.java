package com.github.leandrochp.consumerservice.infrastructure.repositories;

import com.github.leandrochp.consumerservice.domain.entities.Consumer;
import com.github.leandrochp.consumerservice.domain.repositories.ConsumerRepository;
import com.github.leandrochp.consumerservice.infrastructure.repositories.entities.ConsumerEntity;
import com.github.leandrochp.consumerservice.infrastructure.repositories.jpas.ConsumerJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class H2ConsumerRepository implements ConsumerRepository {

    private final ConsumerJpaRepository consumerJpaRepository;

    @Override
    public Page<Consumer> findAll(int page, int size) {
        Page<ConsumerEntity> consumerEntities =
                consumerJpaRepository.findAllPageable(PageRequest.of(page, size));

        return consumerEntities.map(ConsumerEntity::toModel);
    }

    @Override
    public Boolean existsById(int id) {
        return consumerJpaRepository.existsById(id);
    }

    @Override
    public Consumer save(Consumer consumer) {
        ConsumerEntity consumerEntity = ConsumerEntity.toEntity(consumer);
        consumerJpaRepository.save(consumerEntity);

        consumer.setId(consumerEntity.getId());
        return consumer;
    }

    @Override
    public void update(Consumer consumer) {
        consumerJpaRepository.findById(consumer.getId()).ifPresent(
                consumerEntity -> consumerJpaRepository.save(consumerEntity.copyProperties(consumer))
        );
    }
}
