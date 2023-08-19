package com.example.apiservice.controllers;

import com.example.apiservice.dto.request.PartRequest;
import com.example.apiservice.dto.response.PartResponsePaginated;
import com.example.apiservice.services.GateWayService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.ReactiveOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class PartController {

    private final GateWayService gateWayService;
  //  private final OAuth2AuthorizedClientManager oAuth2AuthorizedClientManager; //proxy


    @PostMapping("/part")
    @ResponseStatus(HttpStatus.OK)
    public void savePart(@RequestBody PartRequest partRequest){
        gateWayService.savePart(partRequest);
    }

    @GetMapping("/parts/{page}/{pageSize}")
    public PartResponsePaginated getPaginatedParts(@PathVariable int page, @PathVariable int pageSize, Authentication auth){

//        OAuth2AuthorizeRequest request = OAuth2AuthorizeRequest.withClientRegistrationId("1")
//                .principal("api-client")
//                .build();
//        OAuth2AuthorizedClient client = oAuth2AuthorizedClientManager.authorize(request); //request to auth server
//
//        var x = client.getAccessToken().getTokenValue();

        System.out.println(auth.getAuthorities().toString() + " ++++++++++++++++++++++++++++++++++++++");

        return gateWayService.getPaginatedParts(page, pageSize);
    }

}
