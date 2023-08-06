package com.example.apiservice.controllers;

import com.example.apiservice.dto.request.PartRequest;
import com.example.apiservice.dto.response.PartResponsePaginated;
import com.example.apiservice.services.GateWayService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PartController {

    private final GateWayService gateWayService;

    @PostMapping("/part")
    @ResponseStatus(HttpStatus.OK)
    public void savePart(@RequestBody PartRequest partRequest){
        gateWayService.savePart(partRequest);
    }

    @GetMapping("/parts/{page}/{pageSize}")
    public PartResponsePaginated getPaginatedParts(@PathVariable int page, @PathVariable int pageSize){
        return gateWayService.getPaginatedParts(page, pageSize);
    }

}
