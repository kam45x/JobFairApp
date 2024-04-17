package com.group06.JobFairApp.controller;

import com.group06.JobFairApp.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/company")
public class CompanyPageController {

    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyPageController(CompanyRepository companyRepository)
    {
        this.companyRepository = companyRepository;
    }

    @GetMapping("/page/{id}")
    public String showCompanyPage(@PathVariable("id") Long id, Model model)
    {
        model.addAttribute("page_company", companyRepository.findById(id));
        return "company_page";
    }
}
