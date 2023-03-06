package com.github.leandrochp.consumerpatservice.infrastructure.repositories;

import com.github.leandrochp.consumerpatservice.domain.entities.Extract;
import com.github.leandrochp.consumerpatservice.domain.repositories.ExtractRepository;
import com.github.leandrochp.consumerpatservice.infrastructure.repositories.entities.ExtractEntity;
import com.github.leandrochp.consumerpatservice.infrastructure.repositories.jpas.ExtractJpaRepository;
import com.github.leandrochp.consumerpatservice.infrastructure.repositories.wrappers.ExtractEntityWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class H2ExtractRepository implements ExtractRepository {

    private final ExtractJpaRepository extractRepository;

    @Autowired
    public H2ExtractRepository(final ExtractJpaRepository extractJpaRepository) {
        this.extractRepository = extractJpaRepository;
    }

    @Override
    public void save(Extract extract) {
        ExtractEntity extractEntity
                = new ExtractEntityWrapper(new ExtractEntity()).copyFromProperties(extract);
        extractRepository.save(extractEntity);
    }

    @Override
    public List<Extract> findAll(int consumerId, int page, int size) {
        List<Extract> extracts = new ArrayList<>();

        Page<ExtractEntity> extractEntities =
                extractRepository.findAllPageable(consumerId, PageRequest.of(page, size));
        for (ExtractEntity extractEntity: extractEntities) {
            extracts.add(extractEntity.toModel());
        }
        return extracts;
    }
}