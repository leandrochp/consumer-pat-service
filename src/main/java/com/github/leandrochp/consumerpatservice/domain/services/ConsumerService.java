package com.github.leandrochp.consumerpatservice.domain.services;

import com.github.leandrochp.consumerpatservice.domain.entities.Consumer;
import com.github.leandrochp.consumerpatservice.domain.exceptions.ConsumerNotFoundException;
import com.github.leandrochp.consumerpatservice.domain.exceptions.ConsumersNotFoundException;

import java.util.List;

public interface ConsumerService {

    List<Consumer> findAll(int page, int size) throws ConsumersNotFoundException;

    Consumer save(Consumer consumer);

    Consumer update(Consumer consumer) throws ConsumerNotFoundException;
}
