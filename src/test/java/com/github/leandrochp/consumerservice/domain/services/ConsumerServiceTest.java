package com.github.leandrochp.consumerservice.domain.services;

import com.github.leandrochp.consumerservice.domain.entities.Consumer;
import com.github.leandrochp.consumerservice.domain.entities.ConsumerMock;
import com.github.leandrochp.consumerservice.domain.exceptions.ConsumerNotFoundException;
import com.github.leandrochp.consumerservice.domain.exceptions.ConsumersNotFoundException;
import com.github.leandrochp.consumerservice.domain.repositories.ConsumerRepository;
import com.github.leandrochp.consumerservice.domain.services.impl.ConsumerServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ConsumerServiceTest {

    @InjectMocks
    private ConsumerServiceImpl consumerService;
    @Mock
    private ConsumerRepository consumerRepository;

    @Test
    void whenFindConsumersShouldReturnConsumers() {
        List<Consumer> consumers = List.of(mock(Consumer.class));
        when(consumerRepository.findAll(anyInt(), anyInt())).thenReturn(new PageImpl<>(consumers));

        Page<Consumer> result = consumerService.findAll(0, 1);

        assertNotNull(result);
        assertNotNull(result.getContent());
        assertEquals(1, result.getTotalElements());
    }

    @Test
    void whenFindConsumersThereWereNotFoundShouldThrowConsumersNotFoundException() {
        List<Consumer> consumers = List.of();
        when(consumerRepository.findAll(anyInt(), anyInt())).thenReturn(new PageImpl<>(consumers));

        RuntimeException exception =
                assertThrows(
                        ConsumersNotFoundException.class,
                        () -> consumerService.findAll(0, 1)
                );

        assertNotNull(exception);
        assertEquals("There were not found consumers", exception.getMessage());
    }

    @Test
    void givenAConsumerShouldSaveWithSuccess() {
        Consumer consumerMock = ConsumerMock.sample();
        when(consumerRepository.save(consumerMock)).thenReturn(consumerMock);

        Consumer result = consumerService.save(consumerMock);
        assertNotNull(result);
    }

    @Test
    void givenAConsumerFoundShouldUpdateWithSuccess() {
        Consumer consumerMock = ConsumerMock.sample();
        when(consumerRepository.existsById(anyInt())).thenReturn(true);

        assertDoesNotThrow(() -> consumerService.update(consumerMock));
    }

    @Test
    void givenAConsumerIsNotFoundShouldThrowConsumerNotFoundException() {
        Consumer consumerMock = ConsumerMock.sample();
        when(consumerRepository.existsById(anyInt())).thenReturn(false);

        RuntimeException exception =
                assertThrows(
                        ConsumerNotFoundException.class,
                        () -> consumerService.update(consumerMock)
                );

        assertNotNull(exception);
        assertEquals("The consumer not found", exception.getMessage());
    }

}
