package com.github.leandrochp.consumerservice.domain.service;

import com.github.leandrochp.consumerservice.domain.consumer.Consumer;
import com.github.leandrochp.consumerservice.domain.consumer.ConsumerMock;
import com.github.leandrochp.consumerservice.domain.exception.ConsumerNotFoundException;
import com.github.leandrochp.consumerservice.domain.exception.ConsumersNotFoundException;
import com.github.leandrochp.consumerservice.domain.repository.ConsumerRepository;
import com.github.leandrochp.consumerservice.domain.service.impl.ConsumerServiceImpl;
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
    void whenFindConsumers_shouldReturnConsumers() {
        List<Consumer> consumers = List.of(mock(Consumer.class));

        when(consumerRepository.findAll(anyInt(), anyInt())).thenReturn(new PageImpl<>(consumers));

        Page<Consumer> result = consumerService.findAll(0, 1);

        assertNotNull(result);
        assertNotNull(result.getContent());
        assertEquals(1, result.getTotalElements());
    }

    @Test
    void whenFindConsumersThereWereNotFound_shouldThrowConsumersNotFoundException() {
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
    void whenSaveAConsumer_shouldSaveWithSuccess() {
        Consumer consumerMock = ConsumerMock.sample();

        when(consumerRepository.save(consumerMock)).thenReturn(consumerMock);

        Consumer result = consumerService.save(consumerMock);
        assertNotNull(result);
    }

    @Test
    void whenUpdateAConsumer_shouldUpdateWithSuccess() {
        Consumer consumerMock = ConsumerMock.sample();

        when(consumerRepository.existsById(anyInt())).thenReturn(true);

        assertDoesNotThrow(() -> consumerService.update(consumerMock));
    }

    @Test
    void givenAConsumerNotFound_shouldThrowConsumerNotFoundException() {
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
