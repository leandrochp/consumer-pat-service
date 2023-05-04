package com.github.leandrochp.consumerservice.application.web.controllers;

import com.github.leandrochp.consumerservice.application.web.mappers.SettlementMapper;
import com.github.leandrochp.consumerservice.application.web.requests.SettlementRequest;
import com.github.leandrochp.consumerservice.domain.services.CardService;
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
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void settlement(
            @RequestBody @NotNull SettlementRequest settlementRequest
    ) {
        cardService.updateBalance(mapper.toModel(settlementRequest));
    }
}
