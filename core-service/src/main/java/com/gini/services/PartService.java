package com.gini.services;

import com.gini.dto.request.PartRequest;
import com.gini.dto.response.PartResponsePaginated;
import com.gini.mapper.PartMapper;
import com.gini.model.Part;
import com.gini.repositories.PartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
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

    @Transactional(readOnly = true)
    public PartResponsePaginated getPartsWithPagination(int page, int pageSize) {

        var pageRequest = PageRequest.of(page, pageSize);
        var partPage = partRepository.getPartsWithPagination(pageRequest);

        var totalNrOfParts = partPage.getTotalElements();

        var listOfParts = partPage.stream()
                .map(PartMapper::mapForm)
                .toList();

        return new PartResponsePaginated(
                listOfParts,
                totalNrOfParts
        );

    }
}
