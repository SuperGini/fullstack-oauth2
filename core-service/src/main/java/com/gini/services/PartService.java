package com.gini.services;

import com.gini.dto.request.PartRequest;
import com.gini.mapper.PartMapper;
import com.gini.model.Part;
import com.gini.repositories.PartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PartService {

    private final PartRepository partRepository;

    public void savePart(PartRequest partRequest) {
        Part part = PartMapper.mapFrom(partRequest);
        partRepository.save(part);

    }
}
