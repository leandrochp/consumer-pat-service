package com.github.leandrochp.consumerservice.domain.services.impl;

import com.github.leandrochp.consumerservice.domain.consumer.Consumer;
import com.github.leandrochp.consumerservice.domain.exceptions.ConsumerNotFoundException;
import com.github.leandrochp.consumerservice.domain.exceptions.ConsumersNotFoundException;
import com.github.leandrochp.consumerservice.domain.repositories.ConsumerRepository;
import com.github.leandrochp.consumerservice.domain.services.ConsumerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class ConsumerServiceImpl implements ConsumerService {

    private final ConsumerRepository consumerRepository;

    @Override
    public Page<Consumer> findAll(int page, int size) {
        log.info("Finding consumers");
        val consumers = consumerRepository.findAll(page, size);
        if (consumers.isEmpty()) {
            val message = "There were not found consumers";
            log.warn(message);
            throw new ConsumersNotFoundException(message);
        }
        return consumers;
    }

    @Override
    public Consumer save(Consumer consumer) {
        log.info("Saving consumer");
        return consumerRepository.save(consumer);
    }

    @Override
    public void update(Consumer consumer) {
        log.info("Updating consumer");
        if (!consumerRepository.existsById(consumer.getId())) {
            val message = "The consumer not found";
            log.error(message);

            throw new ConsumerNotFoundException(message);
        }
        consumerRepository.update(consumer);
    }

}
