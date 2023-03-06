package com.github.leandrochp.consumerpatservice.infrastructure.repositories.wrappers;

import com.github.leandrochp.consumerpatservice.domain.entities.Extract;
import com.github.leandrochp.consumerpatservice.infrastructure.repositories.entities.ExtractEntity;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@NoArgsConstructor
public class ExtractEntityWrapper {

    private ExtractEntity extractEntity;

    public ExtractEntityWrapper(ExtractEntity extractEntity) {
        this.extractEntity = extractEntity;
    }

    public ExtractEntity copyFromProperties(Extract extract) {
        BeanUtils.copyProperties(extract, extractEntity);
        return extractEntity;
    }
}
