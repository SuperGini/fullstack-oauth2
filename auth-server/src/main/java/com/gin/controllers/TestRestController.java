package com.gin.controllers;

import com.gin.converter.UserConvertor;
import com.gin.dto.request.UserRequest;
import com.gin.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class TestRestController {

    private final UserService userService;


    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveUser(@RequestBody UserRequest userRequest) {

        var x = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();

        System.out.println(x);

        userService.createUser(UserConvertor.convertFrom(userRequest));
    }


}
