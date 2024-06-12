package com.group06.JobFairApp.controller;

import com.group06.JobFairApp.model.Booth;
import com.group06.JobFairApp.model.Company;
import com.group06.JobFairApp.repository.BoothRepository;
import com.group06.JobFairApp.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/adminView")
public class AdminController {

    private final CompanyRepository companyRepository;
    private final BoothRepository boothRepository;

    @Autowired
    public AdminController(CompanyRepository companyRepository, BoothRepository boothRepository) {
        this.companyRepository = companyRepository;
        this.boothRepository = boothRepository;
    }

    @GetMapping
    public String adminView(Model model) {
        List<Company> companies = companyRepository.findAll();
        companies.sort(Comparator.comparingInt(Company::getBoothNumber));

        model.addAttribute("companiesNumber", companies.size());
        model.addAttribute("companies", companies);
        return "adminview/adminView";
    }

    @GetMapping("/deleteCompany/{id}")
    public String deleteCompany(@PathVariable("id") Long id) {
        Company company = companyRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid company Id:" + id));
        Booth booth = boothRepository.findByBoothNumber(company.getBoothNumber());

        companyRepository.delete(company);

        booth.setCompanyName("-");
        booth.setColor("#ddd");
        boothRepository.save(booth);
        return "redirect:/adminView";
    }
}
