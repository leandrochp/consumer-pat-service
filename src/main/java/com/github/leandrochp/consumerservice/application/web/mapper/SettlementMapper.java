package com.github.leandrochp.consumerservice.application.web.mapper;

import com.github.leandrochp.consumerservice.application.web.request.SettlementRequest;
import com.github.leandrochp.consumerservice.domain.dto.Settlement;
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
