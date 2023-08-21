package com.gini.services;

import com.gini.dto.request.PartRequest;
import com.gini.dto.response.PartResponse;
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
                .map(PartService::calculatePartTotal)
                .toList();
        long total = listOfParts.stream()
                .mapToLong(PartResponse::getPartTotal)
                .sum();

        return new PartResponsePaginated(
                listOfParts,
                totalNrOfParts,
                total
        );
    }

    @Transactional(readOnly = true)
    public PartResponsePaginated getPartsByPartName(int page, int pageSize, String partName){
        var pageRequest = PageRequest.of(page, pageSize);
        var partPage = partRepository.getPartByPartName(pageRequest, partName);

        var totalNrOfParts = partPage.getTotalElements();

        var listOfParts = partPage.stream()
                .map(PartMapper::mapForm)
                .map(PartService::calculatePartTotal)
                .toList();
        long total = listOfParts.stream()
                .mapToLong(PartResponse::getPartTotal)
                .sum();

        return new PartResponsePaginated(
                listOfParts,
                totalNrOfParts,
                total
        );

    }

    private static PartResponse calculatePartTotal(PartResponse x) {
        long partTotal = x.getPrice().longValue() * x.getQuantity();
        x.setPartTotal(partTotal);
        return x;
    }
}
