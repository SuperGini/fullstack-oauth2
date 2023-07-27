package com.gin.controllers;

import com.gin.converter.UserConvertor;
import com.gin.dto.request.UserRequest;
import com.gin.dto.response.UserResponse;
import com.gin.models.User;
import com.gin.repositories.UserRepository;
import com.gin.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TestRestController {

    private final UserService userService;

    @PostMapping("/userx")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveUser(@RequestBody UserRequest userRequest) {

        var x = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();

        System.out.println(x);

        userService.createUser(UserConvertor.convertFrom(userRequest));
    }


    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public List<UserResponse> getAllUsers(){
       return userService.getAllUsers();
    }


}
