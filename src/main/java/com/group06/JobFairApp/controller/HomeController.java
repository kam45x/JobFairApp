package com.group06.JobFairApp.controller;

import com.group06.JobFairApp.FilterForm;
import com.group06.JobFairApp.repository.BoothRepository;
import com.group06.JobFairApp.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

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
    }

    @GetMapping("/")
    public String home(Model model)
    {
        model.addAttribute("companies", companyRepository.findAll());
        model.addAttribute("booths", boothRepository.findAll());
        model.addAttribute("filterForm", filterForm);
        model.addAttribute("selectedFilters", filterForm.getSelectedFilters());
        return "home";
    }

    @PostMapping("/applyFilters")
    public String applyFilters(@ModelAttribute FilterForm filterForm, Model model) {
        this.filterForm = filterForm;
        return "redirect:/";
    }
}
