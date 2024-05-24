package com.group06.JobFairApp.controller;

import com.group06.JobFairApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signin")
    public String signUpUser(@RequestParam String email, @RequestParam String password,
                             @RequestParam String name, @RequestParam String surname,
                             @RequestParam String phoneNumber) {

        String roles = "USER";

        userService.createUser(email, password, name, surname, phoneNumber, roles);
        return "redirect:/login";
    }

    // Metoda loginUser nie jest potrzebna, ponieważ Spring Security obsługuje logowanie
}
