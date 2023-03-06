package com.github.leandrochp.consumerpatservice.domain.services;

import com.github.leandrochp.consumerpatservice.domain.entities.Extract;
import com.github.leandrochp.consumerpatservice.domain.exceptions.ExtractNotFoundException;

import java.util.List;

public interface ExtractService {

    void save(Extract extract);

    List<Extract> findAll(int consumerId, int page, int size) throws ExtractNotFoundException;
}
