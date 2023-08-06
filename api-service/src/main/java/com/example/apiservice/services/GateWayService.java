package com.example.apiservice.services;

import com.example.apiservice.client.CoreServiceClient;
import com.example.apiservice.dto.request.PartRequest;
import com.example.apiservice.dto.response.PartResponsePaginated;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GateWayService {

    private final CoreServiceClient coreServiceClient;

    public void savePart(PartRequest partRequest) {
        coreServiceClient.savePart(partRequest);
    }

    public PartResponsePaginated getPaginatedParts(int page, int pageSize) {
        return coreServiceClient.getPaginatedParts(page, pageSize);
    }


}
