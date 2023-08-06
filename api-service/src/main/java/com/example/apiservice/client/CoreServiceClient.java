package com.example.apiservice.client;

import com.example.apiservice.dto.request.PartRequest;
import com.example.apiservice.dto.response.PartResponsePaginated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;

public interface CoreServiceClient {

    @PostExchange("/part")
    void savePart(@RequestBody PartRequest partRequest);

    @GetExchange("/parts/{page}/{pageSize}")
    PartResponsePaginated getPaginatedParts(@PathVariable int page,
                                            @PathVariable int pageSize);

}
