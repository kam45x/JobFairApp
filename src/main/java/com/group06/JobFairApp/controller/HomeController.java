package com.group06.JobFairApp.controller;

import com.group06.JobFairApp.repository.BoothRepository;
import com.group06.JobFairApp.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final CompanyRepository companyRepository;
    private final BoothRepository boothRepository;

    @Autowired
    public HomeController(CompanyRepository companyRepository, BoothRepository boothRepository) {
        this.companyRepository = companyRepository;
        this.boothRepository = boothRepository;
    }

    @GetMapping("/")
    public String home(Model model)
    {
        model.addAttribute("companies", companyRepository.findAll());
        model.addAttribute("booths", boothRepository.findAll());
        return "home";
    }
}
