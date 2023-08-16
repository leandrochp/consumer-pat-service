package com.github.leandrochp.consumerservice.application.web.mapper;

import com.github.leandrochp.consumerservice.application.web.request.ConsumerRequest;
import com.github.leandrochp.consumerservice.application.web.response.ConsumerResponse;
import com.github.leandrochp.consumerservice.domain.consumer.Consumer;
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
