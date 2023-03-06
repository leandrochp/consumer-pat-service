package com.github.leandrochp.consumerpatservice.domain.services.impl;

import com.github.leandrochp.consumerpatservice.domain.entities.Extract;
import com.github.leandrochp.consumerpatservice.domain.exceptions.ErrorMessages;
import com.github.leandrochp.consumerpatservice.domain.exceptions.ExtractNotFoundException;
import com.github.leandrochp.consumerpatservice.domain.repositories.ExtractRepository;
import com.github.leandrochp.consumerpatservice.domain.services.ExtractService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ExtractServiceImpl implements ExtractService {

    @Autowired
    ExtractRepository extractRepository;

    @Override
    public void save(Extract extract) {
        extractRepository.save(extract);
    }

    @Override
    public List<Extract> findAll(int consumerId, int page, int size) throws ExtractNotFoundException {
        List<Extract> extracts = extractRepository.findAll(consumerId, page, size);
        if (extracts.isEmpty()) {
            String message = String.format(ErrorMessages.EXTRACTS_NOT_FOUND.message(), consumerId);
            log.warn(message);

            throw new ExtractNotFoundException(message);
        }
        return extracts;
    }
}
