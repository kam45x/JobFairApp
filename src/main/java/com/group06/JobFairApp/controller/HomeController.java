package com.group06.JobFairApp.controller;

import com.group06.JobFairApp.FilterForm;
import com.group06.JobFairApp.model.Users;
import com.group06.JobFairApp.repository.BoothRepository;
import com.group06.JobFairApp.repository.CompanyRepository;
import com.group06.JobFairApp.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    private final CompanyRepository companyRepository;
    private final BoothRepository boothRepository;
    private final AuthenticationService authenticationService;
    private FilterForm filterForm;

    @Autowired
    public HomeController(CompanyRepository companyRepository, BoothRepository boothRepository,
                          AuthenticationService authenticationService) {
        this.companyRepository = companyRepository;
        this.boothRepository = boothRepository;
        this.authenticationService = authenticationService;
        this.filterForm = new FilterForm();
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("companies", companyRepository.findAll());
        model.addAttribute("booths", boothRepository.findAll());
        model.addAttribute("filterForm", filterForm);
        model.addAttribute("selectedFilters", filterForm.getSelectedFilters());

        Users user = authenticationService.getAuthenticatedUser();
        if (user != null) {
            model.addAttribute("user", user);
            model.addAttribute("authenticated", true);
            model.addAttribute("username", user.getUsername());

            System.out.println("User " + user.getName() + " with email " + user.getEmail() + " is authenticated");
        } else {
            model.addAttribute("authenticated", false);

            System.out.println("Authenticated is false");
        }

        return "home";
    }

    @PostMapping("/applyFilters")
    public String applyFilters(@ModelAttribute FilterForm filterForm, Model model) {
        this.filterForm = filterForm;
        return "redirect:/";
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping("/signin")
    public String showSignUpPage() {
        return "signin";
    }
}
