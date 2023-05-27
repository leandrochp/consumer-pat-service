package com.github.leandrochp.consumerservice.application.web.controllers;

import com.github.leandrochp.consumerservice.domain.exceptions.CardNotFoundException;
import com.github.leandrochp.consumerservice.domain.exceptions.EstablishmentTypeException;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@SqlGroup({
        @Sql(value = "classpath:db/reset.sql", executionPhase = BEFORE_TEST_METHOD),
        @Sql(value = "classpath:db/card/insert_card.sql", executionPhase = BEFORE_TEST_METHOD)
})
@ActiveProfiles("test")
public class SettlementControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void givenACardNumberWithTypeAccepted_shouldAddBalanceWithSuccessWithStatusNoContent() throws Exception {
        String cardNumber = "1234567890";
        mockMvc.perform(
                        post("/settlement")
                                .content(
                                        String.format(
                                                TestUtils.readFile("settlement/settlement_card_type_meal"),
                                                cardNumber
                                        )
                                ).contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNoContent());
    }

    @Test
    void whenASettlementWithACardNumberNotFound_shouldResponseTheCardNumberNotFoundWithStatusNotFound() throws Exception {
        String cardNumber = "0987654321";
        mockMvc.perform(
                        post("/settlement")
                                .content(
                                        String.format(
                                                TestUtils.readFile("settlement/settlement_card_type_meal"),
                                                cardNumber
                                        )
                                ).contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNotFound())
                .andExpect(
                        result -> assertTrue(result.getResolvedException() instanceof CardNotFoundException)
                )
                .andExpect(jsonPath("status").value("NOT_FOUND"))
                .andExpect(jsonPath("message").value("The card number not found"));
    }

    @Test
    void whenASettlementWithACardNumberWithTypeNotAccepted_shouldResponseTheCardTypeDoesNotAcceptWithStatusBadRequest() throws Exception {
        String cardNumber = "1234567890";
        mockMvc.perform(
                        post("/settlement")
                                .content(
                                        String.format(
                                                TestUtils.readFile("settlement/settlement_card_type_pharmacy"),
                                                cardNumber
                                        )
                                ).contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest())
                .andExpect(
                        result -> assertTrue(result.getResolvedException() instanceof EstablishmentTypeException)
                )
                .andExpect(jsonPath("status").value("BAD_REQUEST"))
                .andExpect(
                        jsonPath("message").value("The card type does not accept this establishment type")
                );
    }

}
