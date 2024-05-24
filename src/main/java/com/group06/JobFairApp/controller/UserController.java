package com.group06.JobFairApp.controller;

import com.group06.JobFairApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.regex.Pattern;

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
                             @RequestParam String phoneNumber, @RequestParam String repeatedPassword,
                             RedirectAttributes redirectAttributes) {
        try {
            String roles = "USER";

            if (!password.equals(repeatedPassword)) {
                redirectAttributes.addFlashAttribute("errorMessage",
                        "Hasło i powtórzone hasło muszą być takie same");
                RedirectFormAttributes(name, surname, phoneNumber, redirectAttributes);
                return "redirect:/signin";
            }
            if (!isPasswordValid(password)) {
                redirectAttributes.addFlashAttribute("errorMessage",
                        "Hasło musi zawierać małą literę, dużą literę, cyfrę i znak specjalny");
                RedirectFormAttributes(name, surname, phoneNumber, redirectAttributes);
                return "redirect:/signin";
            }
            userService.createUser(email, password, name, surname, phoneNumber, roles);
            return "redirect:/login";
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Użytkownik o podanym adresie e-mail już istnieje");
            RedirectFormAttributes(name, surname, phoneNumber, redirectAttributes);
            return "redirect:/signin";
        }
    }

    private void RedirectFormAttributes(String name, String surname, String phoneNumber,
                                        RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("name", name);
        redirectAttributes.addFlashAttribute("surname", surname);
        redirectAttributes.addFlashAttribute("phoneNumber", phoneNumber);
    }

    private boolean isPasswordValid(String password) {
        return Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).+$").matcher(password).matches();
    }

}
