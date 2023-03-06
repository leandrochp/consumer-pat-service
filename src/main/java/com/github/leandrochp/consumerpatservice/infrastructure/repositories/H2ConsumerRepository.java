package com.github.leandrochp.consumerpatservice.infrastructure.repositories;

import com.github.leandrochp.consumerpatservice.domain.entities.Consumer;
import com.github.leandrochp.consumerpatservice.domain.repositories.ConsumerRepository;
import com.github.leandrochp.consumerpatservice.infrastructure.repositories.entities.ConsumerEntity;
import com.github.leandrochp.consumerpatservice.infrastructure.repositories.jpas.ConsumerJpaRepository;
import com.github.leandrochp.consumerpatservice.infrastructure.repositories.wrappers.ConsumerEntityWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class H2ConsumerRepository implements ConsumerRepository {

    private final ConsumerJpaRepository consumerRepository;

    @Autowired
    public H2ConsumerRepository(final ConsumerJpaRepository consumerRepository) {
        this.consumerRepository = consumerRepository;
    }

    @Override
    public List<Consumer> findAll(int page, int size) {
        List<Consumer> consumers = new ArrayList<>();

        Page<ConsumerEntity> consumerEntities =
                consumerRepository.findAllPageable(PageRequest.of(page, size));
        for (ConsumerEntity consumerEntity : consumerEntities) {
            consumers.add(consumerEntity.toModel());
        }
        return consumers;
    }

    @Override
    public Boolean findById(int id) {
        return consumerRepository.existsById(id);
    }

    @Override
    public Consumer save(Consumer consumer) {
        ConsumerEntity consumerEntity =
                new ConsumerEntityWrapper(new ConsumerEntity()).copyFromProperties(consumer);

        consumerRepository.save(consumerEntity);
        consumer.setId(consumerEntity.getId());
        return consumer;
    }

    @Override
    public Consumer update(Consumer consumer) {
        Optional<ConsumerEntity> consumerEntity = consumerRepository.findById(consumer.getId());

        consumerEntity.ifPresent(
                entity -> entity = new ConsumerEntityWrapper(entity).copyFromProperties(consumer)
        );
        consumerRepository.save(consumerEntity.get());
        return consumer;
    }
}
