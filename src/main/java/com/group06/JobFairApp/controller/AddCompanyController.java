package com.group06.JobFairApp.controller;

import com.group06.JobFairApp.model.Company;
import com.group06.JobFairApp.repository.BoothRepository;
import com.group06.JobFairApp.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("adminView/addCompany")
public class AddCompanyController {

    private final CompanyRepository companyRepository;
    private final BoothRepository boothRepository;

    private List<Integer> freeBoothNumbers;

    @Autowired
    public AddCompanyController(CompanyRepository companyRepository, BoothRepository boothRepository) {
        this.companyRepository = companyRepository;
        this.boothRepository = boothRepository;
    }

    @GetMapping
    public String adminPage(Model model) {
        List<Integer> takenBoothNumbers = companyRepository.findAll()
                .stream()
                .map(Company::getBoothNumber)
                .toList();

        int totalBooths = (int) boothRepository.count();

        freeBoothNumbers = IntStream.rangeClosed(1, totalBooths)
                .boxed()
                .filter(boothNumber -> !takenBoothNumbers.contains(boothNumber))
                .collect(Collectors.toList());

        model.addAttribute("company", new Company());
        model.addAttribute("freeBoothNumbers", freeBoothNumbers);
        return "adminview/addCompany";
    }

    @PostMapping
    public String addCompany(@ModelAttribute Company company, BindingResult result, Model model) {
        if (companyRepository.findByName(company.getName()) != null) {
            result.rejectValue("name", "error.company", "Nazwa firmy jest już zajęta");
        }
        if (companyRepository.findByWebsiteUrl(company.getWebsiteUrl()) != null) {
            result.rejectValue("websiteUrl", "error.company", "Podany adres strony internetowej jest już wykorzystywany przez inną firmę");
        }

        if (result.hasErrors()) {

            model.addAttribute("freeBoothNumbers", freeBoothNumbers);
            return "adminview/addCompany";
        }

        companyRepository.save(company);
        return "redirect:/adminView";
    }
}
