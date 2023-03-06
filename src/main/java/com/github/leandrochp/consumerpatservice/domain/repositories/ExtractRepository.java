package com.github.leandrochp.consumerpatservice.domain.repositories;

import com.github.leandrochp.consumerpatservice.domain.entities.Extract;

import java.util.List;

public interface ExtractRepository {

    void save(Extract extract);

    List<Extract> findAll(int consumerId, int page, int size);
}
