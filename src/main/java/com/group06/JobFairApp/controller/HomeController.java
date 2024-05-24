package com.group06.JobFairApp.controller;

import com.group06.JobFairApp.FilterForm;
import com.group06.JobFairApp.model.Booth;
import com.group06.JobFairApp.model.Company;
import com.group06.JobFairApp.repository.BoothRepository;
import com.group06.JobFairApp.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    private final CompanyRepository companyRepository;
    private final BoothRepository boothRepository;
    private FilterForm filterForm;

    @Autowired
    public HomeController(CompanyRepository companyRepository, BoothRepository boothRepository) {
        this.companyRepository = companyRepository;
        this.boothRepository = boothRepository;
        this.filterForm = new FilterForm();
        this.colorBooths();
    }

    @GetMapping("/")
    public String home(Model model)
    {
        model.addAttribute("companies", companyRepository.findAll());

        // Sort booths before adding to the model
        List<Booth> booths = boothRepository.findAll();
        List<Booth> sortedBooths = booths.stream()
                .sorted(Comparator.comparing(Booth::getBoothNumber))
                .collect(Collectors.toList());
        model.addAttribute("booths", sortedBooths);

        model.addAttribute("filterForm", filterForm);
        model.addAttribute("selectedFilters", filterForm.getSelectedFilters());

        return "home";
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
                boothOptional.ifPresent(booth -> booth.setColorByName("green"));
            }
            else {
                boothOptional.ifPresent(booth -> booth.setColorByName("gray"));
            }
            boothOptional.ifPresent(boothRepository::save);
        }
    }
}
