package com.github.leandrochp.consumerservice.application.web.controllers;


import com.github.leandrochp.consumerservice.domain.exceptions.ConsumersNotFoundException;
import com.github.leandrochp.consumerservice.utils.TestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@SqlGroup({
        @Sql(value = "classpath:db/reset.sql", executionPhase = BEFORE_TEST_METHOD),
        @Sql(value = "classpath:db/consumer/insert_consumer.sql", executionPhase = BEFORE_TEST_METHOD)
})
@ActiveProfiles("test")
public class ConsumerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void whenFindConsumers_shouldReturnConsumersWithStatusOk() throws Exception {
        mockMvc.perform(
                    get("/consumers")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("content").isArray())
                .andExpect(jsonPath("content", hasSize(1)))
                .andExpect(jsonPath("content[0].id").isNotEmpty());
}

    @Test
    void whenCreateConsumer_shouldCreateWithSuccessWithStatusCreated() throws Exception {
        mockMvc.perform(
                    post("/consumers")
                        .content(TestUtils.readFile("consumer/create_consumer"))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").isNotEmpty());
    }

    @Test
    void whenUpdateConsumer_shouldUpdateWithSuccessWithStatusNoContent() throws Exception {
        mockMvc.perform(
                    patch("/consumers")
                        .content(TestUtils.readFile("consumer/update_consumer"))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNoContent());
    }

    @Sql(value = "classpath:db/reset.sql", executionPhase = BEFORE_TEST_METHOD)
    @Test
    void whenFindConsumers_shouldResponseThereWereNotFoundWithStatusNotFound() throws Exception {
        mockMvc.perform(
                    get("/consumers")
                )
                .andExpect(status().isNotFound())
                .andExpect(
                        result -> assertTrue(result.getResolvedException() instanceof ConsumersNotFoundException)
                )
                .andExpect(jsonPath("status").value("NOT_FOUND"))
                .andExpect(jsonPath("message").value("There were not found consumers"));
    }
}
