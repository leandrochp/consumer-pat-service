package com.github.leandrochp.consumerservice.infrastructure.repository;

import com.github.leandrochp.consumerservice.domain.consumer.Consumer;
import com.github.leandrochp.consumerservice.domain.repository.ConsumerRepository;
import com.github.leandrochp.consumerservice.infrastructure.repository.entity.ConsumerEntity;
import com.github.leandrochp.consumerservice.infrastructure.repository.jpa.ConsumerJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class H2ConsumerRepository implements ConsumerRepository {

    private final ConsumerJpaRepository consumerJpaRepository;

    @Override
    public Page<Consumer> findAll(int page, int size) {
        val consumerEntities =
                consumerJpaRepository.findAllPageable(PageRequest.of(page, size));

        return consumerEntities.map(ConsumerEntity::toModel);
    }

    @Override
    public Boolean existsById(int id) {
        return consumerJpaRepository.existsById(id);
    }

    @Override
    public Consumer save(Consumer consumer) {
        val consumerEntity = ConsumerEntity.toEntity(consumer);
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
