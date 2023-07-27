package com.gin.controllers;

import com.gin.dto.request.Authorities;
import com.gin.dto.request.UserRequest2;
import com.gin.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final UserService userService;


    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("userRequest", new UserRequest2());
        model.addAttribute("auth", Authorities.values());
        return "home";
    }

    @PostMapping("/user")
    public String createUser(@ModelAttribute("userRequest") UserRequest2 userRequest) {

        System.out.println(userRequest);

        return "redirect:home";
    }

}
