package com.gini.controllers;

import com.gini.dto.request.PartRequest;
import com.gini.dto.response.PartResponsePaginated;
import com.gini.services.PartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(
        consumes = APPLICATION_JSON_VALUE,
        produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class PartController {


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
    public PartResponsePaginated getPaginatedParts(@PathVariable int page, @PathVariable int pageSize, Principal principal){

        var x = principal.getName();

        return partService.getPartsWithPagination(page, pageSize);
    }



}
