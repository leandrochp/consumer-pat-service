package com.github.leandrochp.consumerservice.application.web.controller;

import com.github.leandrochp.consumerservice.domain.exception.CardNotFoundException;
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

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@SqlGroup({
        @Sql(value = "classpath:db/reset.sql", executionPhase = BEFORE_TEST_METHOD),
        @Sql(value = "classpath:db/card/insert_card.sql", executionPhase = BEFORE_TEST_METHOD)
})
@ActiveProfiles("test")
public class CardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void givenACardNumber_shouldAddBalanceWithSuccessWithStatusNoContent() throws Exception {
        String cardNumber = "1234567890";
        mockMvc.perform(
                        put("/cards/{cardNumber}/balance", cardNumber)
                                .content(TestUtils.readFile("card/add_balance"))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNoContent());
    }

    @Test
    void whenAddBalanceWithACardNumberNotFound_shouldResponseTheCardNumberNotFoundWithStatusNotFound() throws Exception {
        String cardNumber = "0987654321";
        mockMvc.perform(
                        put("/cards/{cardNumber}/balance", cardNumber)
                                .content(TestUtils.readFile("card/add_balance"))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNotFound())
                .andExpect(
                        result -> assertTrue(result.getResolvedException() instanceof CardNotFoundException)
                )
                .andExpect(jsonPath("status").value("NOT_FOUND"))
                .andExpect(jsonPath("message").value("The card number not found"));
    }

}
