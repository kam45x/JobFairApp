package com.group06.JobFairApp.controller;

import com.group06.JobFairApp.model.Company;
import com.group06.JobFairApp.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final CompanyRepository companyRepository;

    @Autowired
    public AdminController(CompanyRepository companyRepository)
    {
        this.companyRepository = companyRepository;
    }

    @GetMapping
    private String adminPage()
    {
        return "adminview/addCompany";
    }

    @PostMapping
    private String addCompany(Company company)
    {
        companyRepository.save(company);
        return "redirect:/";
    }
}
