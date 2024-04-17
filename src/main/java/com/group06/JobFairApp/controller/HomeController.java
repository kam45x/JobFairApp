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

    // Konstruktor kontrolera HomeController
    @Autowired
    public HomeController(CompanyRepository companyRepository, BoothRepository boothRepository) {
        this.companyRepository = companyRepository;
        this.boothRepository = boothRepository;
    }

    // Metoda obsługująca żądanie GET dla ścieżki głównej "/"
    @GetMapping("/")
    public String home(Model model)
    {
        // Dodanie atrybutów "companies" i "booths" do modelu, aby były dostępne w widoku
        model.addAttribute("companies", companyRepository.findAll());
        model.addAttribute("booths", boothRepository.findAll());
        return "home";
    }
}
