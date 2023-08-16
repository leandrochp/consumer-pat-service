package com.github.leandrochp.consumerservice.domain.service;

import com.github.leandrochp.consumerservice.domain.consumer.Card;
import com.github.leandrochp.consumerservice.domain.consumer.CardMock;
import com.github.leandrochp.consumerservice.domain.dto.Settlement;
import com.github.leandrochp.consumerservice.domain.dto.SettlementMock;
import com.github.leandrochp.consumerservice.domain.enums.CardType;
import com.github.leandrochp.consumerservice.domain.exception.CardNotFoundException;
import com.github.leandrochp.consumerservice.domain.exception.EstablishmentTypeException;
import com.github.leandrochp.consumerservice.domain.repository.CardRepository;
import com.github.leandrochp.consumerservice.domain.service.impl.CardServiceImpl;
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
    void givenACardNumber_shouldAddBalanceWithSuccess() {
        Card cardMock = CardMock.sample();

        when(cardRepository.findByCardNumber(any())).thenReturn(cardMock);

        cardService.addBalance("123", BigDecimal.valueOf(200));

        assertEquals(BigDecimal.valueOf(400), cardMock.getBalance());
    }

    @Test
    void whenAddBalanceWithACardNumberNotFound_shouldThrowCardNotFoundException() {
        when(cardRepository.findByCardNumber(any())).thenReturn(null);

        CardNotFoundException exception =
                assertThrows(
                        CardNotFoundException.class,
                        () -> cardService.addBalance("123", BigDecimal.ZERO)
                );

        assertNotNull(exception);
        assertEquals("The card number not found", exception.getMessage());
    }

    @Test
    void givenASettlement_shouldUpdateCardBalanceWithSuccess() {
        Card cardMock = CardMock.sample();

        when(cardRepository.findByCardNumber(any())).thenReturn(cardMock);

        cardService.updateBalance(SettlementMock.sample());
        assertEquals(BigDecimal.valueOf(-70.0), cardMock.getBalance());
    }

    @Test
    void whenASettlementWithACardNumberNotFound_shouldThrowCardNotFoundException() {
        when(cardRepository.findByCardNumber(any())).thenReturn(null);

        CardNotFoundException exception =
                assertThrows(
                        CardNotFoundException.class,
                        () -> cardService.updateBalance(Mockito.mock(Settlement.class))
                );

        assertNotNull(exception);
        assertEquals("The card number not found", exception.getMessage());
    }

    @Test
    void whenASettlementWithCardTypeDoesNotAccept_shouldThrowsEstablishmentTypeException() {
        Settlement settlementMock = SettlementMock.sample(CardType.MEAL);

        when(cardRepository.findByCardNumber(any())).thenReturn(CardMock.sample());

        EstablishmentTypeException exception =
                assertThrows(
                        EstablishmentTypeException.class,
                        () -> cardService.updateBalance(settlementMock)
                );

        assertNotNull(exception);
        assertEquals("The card type does not accept this establishment type", exception.getMessage());
    }
}
