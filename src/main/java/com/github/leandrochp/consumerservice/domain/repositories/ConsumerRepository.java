package com.github.leandrochp.consumerservice.domain.repositories;

import com.github.leandrochp.consumerservice.domain.consumer.Consumer;
import org.springframework.data.domain.Page;

public interface ConsumerRepository {

    Page<Consumer> findAll(int page, int size);
    Boolean existsById(int id);
    Consumer save(Consumer consumer);
    void update(Consumer consumer);
}
