package com.group06.JobFairApp.controller;

import com.group06.JobFairApp.model.Company;
import com.group06.JobFairApp.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/adminView")
public class AdminController {

    @Autowired
    private CompanyRepository companyRepository;

    @GetMapping
    public String adminView(Model model) {
        model.addAttribute("companies", companyRepository.findAll());
        return "adminview/adminView";
    }

    @GetMapping("/editCompany/{id}")
    public String editCompanyForm(@PathVariable("id") Long id, Model model) {
        Company company = companyRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid company Id:" + id));
        model.addAttribute("company", company);
        return "adminview/editCompany";
    }

    @PostMapping("/saveCompany")
    public String saveCompany(@ModelAttribute("company") Company company, BindingResult result) {
        if (result.hasErrors()) {
            return "adminview/editCompany";
        }
        companyRepository.save(company);
        return "redirect:/adminView";
    }

    @GetMapping("/deleteCompany/{id}")
    public String deleteCompany(@PathVariable("id") Long id) {
        Company company = companyRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid company Id:" + id));
        companyRepository.delete(company);
        return "redirect:/adminView";
    }
}
