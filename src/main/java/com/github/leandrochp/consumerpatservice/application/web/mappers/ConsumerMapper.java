package com.github.leandrochp.consumerpatservice.application.web.mappers;

import com.github.leandrochp.consumerpatservice.application.web.requests.ConsumerRequest;
import com.github.leandrochp.consumerpatservice.application.web.responses.ConsumerResponse;
import com.github.leandrochp.consumerpatservice.domain.entities.Consumer;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ConsumerMapper {

    private final ModelMapper mapper;

    public Consumer toModel(ConsumerRequest consumerRequest) {
        return mapper.map(consumerRequest, Consumer.class);
    }

    public ConsumerResponse toResponse(Consumer consumer) {
        return mapper.map(consumer, ConsumerResponse.class);
    }
}
