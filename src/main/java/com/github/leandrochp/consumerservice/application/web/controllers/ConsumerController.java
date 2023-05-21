package com.github.leandrochp.consumerservice.application.web.controllers;

import com.github.leandrochp.consumerservice.application.web.mappers.ConsumerMapper;
import com.github.leandrochp.consumerservice.application.web.requests.ConsumerRequest;
import com.github.leandrochp.consumerservice.application.web.responses.ConsumerResponse;
import com.github.leandrochp.consumerservice.domain.consumer.Consumer;
import com.github.leandrochp.consumerservice.domain.services.ConsumerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RequiredArgsConstructor
@RestController
@RequestMapping("/consumers")
public class ConsumerController {

    private final ConsumerService consumerService;
    private final ConsumerMapper mapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<ConsumerResponse> findAll(
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size
    ) {
        return consumerService.findAll(page, size).map(mapper::toResponse);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ConsumerResponse save(@RequestBody @NotNull ConsumerRequest consumerRequest) {
        Consumer consumer = mapper.toModel(consumerRequest);
        return mapper.toResponse(consumerService.save(consumer));
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody @NotNull ConsumerRequest consumerRequest) {
        consumerService.update(mapper.toModel(consumerRequest));
    }
}
