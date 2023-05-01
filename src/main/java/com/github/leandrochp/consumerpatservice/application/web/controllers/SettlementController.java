package com.github.leandrochp.consumerpatservice.application.web.controllers;

import com.github.leandrochp.consumerpatservice.application.web.mappers.SettlementMapper;
import com.github.leandrochp.consumerpatservice.application.web.requests.SettlementRequest;
import com.github.leandrochp.consumerpatservice.domain.services.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RequiredArgsConstructor
@RestController
@RequestMapping("/settlement")
public class SettlementController {

    private final CardService cardService;
    private final SettlementMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void settle(
            @RequestBody @NotNull SettlementRequest settlementRequest
    ) {
        cardService.updateBalance(mapper.toModel(settlementRequest));
    }
}
