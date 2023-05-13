package com.github.leandrochp.consumerservice.domain.services;

import com.github.leandrochp.consumerservice.domain.dtos.Settlement;
import com.github.leandrochp.consumerservice.domain.dtos.SettlementMock;
import com.github.leandrochp.consumerservice.domain.entities.Card;
import com.github.leandrochp.consumerservice.domain.entities.CardMock;
import com.github.leandrochp.consumerservice.domain.exceptions.CardNotFoundException;
import com.github.leandrochp.consumerservice.domain.exceptions.EstablishmentTypeException;
import com.github.leandrochp.consumerservice.domain.repositories.CardRepository;
import com.github.leandrochp.consumerservice.domain.services.impl.CardServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CardServiceTest {

    @InjectMocks
    private CardServiceImpl cardService;
    @Mock
    private CardRepository cardRepository;

    @Test
    void givenACardAddBalanceShouldAddBalanceWithSuccess() {
        Card cardMock = CardMock.sample();
        when(cardRepository.findByCardNumber(any())).thenReturn(cardMock);

        cardService.addBalance("123", BigDecimal.valueOf(200));

        assertEquals(BigDecimal.valueOf(400), cardMock.getBalance());
    }

    @Test
    void whenAddBalanceWithACardNumberIsNotFoundShouldThrowCardNotFoundException() {
        when(cardRepository.findByCardNumber(any())).thenReturn(null);

        RuntimeException exception =
                assertThrows(
                        CardNotFoundException.class,
                        () -> cardService.addBalance("123", BigDecimal.ZERO)
                );

        assertNotNull(exception);
        assertEquals("The card number not found", exception.getMessage());
    }

    @Test
    void givenASettlementShouldUpdateBalanceWithSuccess() {
        Card cardMock = CardMock.sample();
        when(cardRepository.findByCardNumber(any())).thenReturn(cardMock);

        cardService.updateBalance(SettlementMock.sample());
        assertEquals(BigDecimal.valueOf(20.0), cardMock.getBalance());
    }

    @Test
    void whenASettlementWithACardNumberIsNotFoundShouldThrowCardNotFoundException() {
        when(cardRepository.findByCardNumber(any())).thenReturn(null);

        RuntimeException exception =
                assertThrows(
                        CardNotFoundException.class,
                        () -> cardService.updateBalance(Mockito.mock(Settlement.class))
                );

        assertNotNull(exception);
        assertEquals("The card number not found", exception.getMessage());
    }

    @Test
    void whenASettlementWithCardTypeDoesNotAcceptThrowsEstablishmentTypeException() {
        when(cardRepository.findByCardNumber(any())).thenReturn(CardMock.sample());

        RuntimeException exception =
                assertThrows(
                        EstablishmentTypeException.class,
                        () -> cardService.updateBalance(Mockito.mock(Settlement.class))
                );

        assertNotNull(exception);
        assertEquals("The card type does not accept this establishment type", exception.getMessage());
    }

}
