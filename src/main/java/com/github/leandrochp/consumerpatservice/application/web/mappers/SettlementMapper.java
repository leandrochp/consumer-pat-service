package com.github.leandrochp.consumerpatservice.application.web.mappers;

import com.github.leandrochp.consumerpatservice.application.web.requests.SettlementRequest;
import com.github.leandrochp.consumerpatservice.domain.dtos.Settlement;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SettlementMapper {

    private final ModelMapper mapper;

    public Settlement toModel(SettlementRequest settlementRequest) {
        return mapper.map(settlementRequest, Settlement.class);
    }
}
