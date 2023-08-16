package com.github.leandrochp.consumerservice.application.web.controller;

import com.github.leandrochp.consumerservice.application.web.request.BalanceRequest;
import com.github.leandrochp.consumerservice.domain.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RequiredArgsConstructor
@RestController
@RequestMapping("/cards")
public class CardController {

    private final CardService cardService;

    @PutMapping(value = "/{cardNumber}/balance")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addBalance(
            @PathVariable String cardNumber, @RequestBody @NotNull BalanceRequest balanceRequest
    ) {
        cardService.addBalance(cardNumber, balanceRequest.getValue());
    }

}
