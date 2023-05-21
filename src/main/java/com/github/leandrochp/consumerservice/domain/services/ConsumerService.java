package com.github.leandrochp.consumerservice.domain.services;

import com.github.leandrochp.consumerservice.domain.consumer.Consumer;
import org.springframework.data.domain.Page;

public interface ConsumerService {

    Page<Consumer> findAll(int page, int size);
    void update(Consumer consumer);
    Consumer save(Consumer consumer);
}
