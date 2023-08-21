package com.gini.controllers;

import com.gini.dto.request.PartRequest;
import com.gini.dto.response.PartResponsePaginated;
import com.gini.repositories.PartRepository;
import com.gini.services.PartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequestMapping(
        consumes = APPLICATION_JSON_VALUE,
        produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class PartController {
    private final PartRepository partRepository;


    private final PartService partService;

    @GetMapping("/test")
    @ResponseStatus(HttpStatus.OK)
    public String test() {
        return "TEST!";

    }

    @PostMapping("/part")
    @ResponseStatus(HttpStatus.CREATED)
    public void savePart(@RequestBody PartRequest partRequest){
        partService.savePart(partRequest);

    }

    @GetMapping("/parts/{page}/{pageSize}")
    public PartResponsePaginated getPaginatedParts(@PathVariable int page,
                                                   @PathVariable int pageSize,
                                                   Principal principal){
        log.info("User is: {}", principal.getName());
        return partService.getPartsWithPagination(page, pageSize);
    }

    @GetMapping("/parts/{page}/{pageSize}/{partName}")
    public PartResponsePaginated getPaginatedPartsByPartName(@PathVariable int page,
                                                             @PathVariable int pageSize,
                                                             @PathVariable String partName){
        return partService.getPartsByPartName(page, pageSize, partName);
    }

}
