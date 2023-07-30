package com.gin.controllers;

import com.gin.dto.request.UserRequest2;
import com.gin.dto.response.UserResponse;
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

    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveUser(@RequestBody UserRequest2 userRequest2) {

        var x = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();

        System.out.println(x);

        userService.createUser(userRequest2);
    }


    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers();
    }


}
