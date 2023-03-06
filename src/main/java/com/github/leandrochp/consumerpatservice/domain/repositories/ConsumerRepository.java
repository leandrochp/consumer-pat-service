package com.github.leandrochp.consumerpatservice.domain.repositories;

import com.github.leandrochp.consumerpatservice.domain.entities.Consumer;

import java.util.List;

public interface ConsumerRepository {

    List<Consumer> findAll(int page, int size);

    Boolean findById(int id);

    Consumer save(Consumer consumer);

    Consumer update(Consumer consumer);
}
