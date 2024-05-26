package com.group06.JobFairApp.controller;

import com.group06.JobFairApp.FilterForm;
import com.group06.JobFairApp.model.Booth;
import com.group06.JobFairApp.model.Company;
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

import java.util.*;
import java.util.stream.Collectors;

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
        this.colorBooths();
    }

    @GetMapping("/")
    public String home(Model model) {
        // Companies
        model.addAttribute("companies", companyRepository.findAll());

        // Add sorted booths
        List<Booth> booths = boothRepository.findAll();
        Map<Integer, String> companyMap = companyRepository.findAll().stream()
                .collect(Collectors.toMap(Company::getBoothNumber, Company::getName));

        booths.forEach(booth -> {
            if (companyMap.containsKey(booth.getBoothNumber())) {
                booth.setCompanyName(companyMap.get(booth.getBoothNumber()));
            }
        });

        List<Booth> sortedBooths = booths.stream()
                .sorted(Comparator.comparing(Booth::getBoothNumber))
                .collect(Collectors.toList());

        model.addAttribute("booths", sortedBooths);
        this.colorBooths();

        // Filter form
        model.addAttribute("filterForm", filterForm);
        model.addAttribute("selectedFilters", filterForm.getSelectedFilters());

        // All filters
        List<String> allJobTopics = new ArrayList<>();
        List<String> allSkills = new ArrayList<>();
        for (Company company : companyRepository.findAll()) {
            String[] companyJobTopics = company.getJobTopics().split(", ");
            String[] companySkills = company.getSkills().split(", ");

            for (String companyJobTopic : companyJobTopics) {
                if (!allJobTopics.contains(companyJobTopic)) {
                    allJobTopics.add(companyJobTopic);
                }
            }

            for (String companySkill : companySkills) {
                if (!allSkills.contains(companySkill)) {
                    allSkills.add(companySkill);
                }
            }
        }
        model.addAttribute("allJobTopics", allJobTopics);
        model.addAttribute("allSkills", allSkills);

        // Authentication
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

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping("/signin")
    public String showSignUpPage() {
        return "signin";
    }

    @PostMapping("/applyFilters")
    public String applyFilters(@ModelAttribute FilterForm filterForm, Model model) {
        // Update filter form
        this.filterForm = filterForm;

        // Color booths
        this.colorBooths();

        return "redirect:/";
    }

    public void colorBooths() {
        List<Booth> booths = boothRepository.findAll();
        for (Company company : companyRepository.findAll()) {
            Optional<Booth> boothOptional = booths.stream().filter(booth -> booth.getBoothNumber() == company.getBoothNumber()).findFirst();
            if (company.matchesJobTopics(filterForm.getSelectedFilters())) {
                double matchedSkills = company.matchedSkills(filterForm.getSelectedFilters());
                if (matchedSkills < 0.1) {
                    boothOptional.ifPresent(booth -> booth.setColorByName("green1"));
                }
                else if (matchedSkills > 0.1 && matchedSkills < 0.7) {
                    boothOptional.ifPresent(booth -> booth.setColorByName("green2"));
                }
                else {
                    boothOptional.ifPresent(booth -> booth.setColorByName("green3"));
                }
            }
            else {
                boothOptional.ifPresent(booth -> booth.setColorByName("dimGray"));
            }
            boothOptional.ifPresent(boothRepository::save);
        }
    }
}
