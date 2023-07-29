package com.gin.controllers;

import com.gin.converter.UserConvertor;
import com.gin.dto.request.Authorities;
import com.gin.dto.request.UserRequest2;
import com.gin.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final UserService userService;


    @GetMapping("/home")
    public String home(Model model) {
        setupTheModel(model);
        return "home";
    }

    @PostMapping("/createUser")
    public String createUser(@ModelAttribute("userRequest") UserRequest2 userRequest) {
        userService.createUser(UserConvertor.convertFrom(userRequest));
        return "redirect:home";
    }

    @DeleteMapping("/deleteUser")
    public String deleteUser(@RequestParam UUID id){
        userService.deleteUser(id);
        return "redirect:home";
    }

    private void setupTheModel(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("userRequest", new UserRequest2());
        model.addAttribute("auth", Authorities.values());
    }

}
