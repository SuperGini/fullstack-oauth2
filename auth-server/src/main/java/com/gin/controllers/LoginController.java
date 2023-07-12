package com.gin.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @GetMapping("/login2")
    public String loginPage(Model model) {
        return "login";
    }

    @PostMapping("/login/user")
    public String loginUser(Model model) {



        return "login";
    }


}
