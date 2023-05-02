package com.github.leandrochp.consumerservice.application.web.requests;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class BalanceRequest {
    private BigDecimal value;
}
