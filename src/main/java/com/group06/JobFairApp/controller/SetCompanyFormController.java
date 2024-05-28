package com.group06.JobFairApp.controller;

import com.group06.JobFairApp.model.Company;
import com.group06.JobFairApp.repository.BoothRepository;
import com.group06.JobFairApp.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("adminView/")
public class SetCompanyFormController {

    private final CompanyRepository companyRepository;
    private final BoothRepository boothRepository;
    private final List<String> availableJobTopics = Arrays.asList(
            "Administracja",
            "Architektura",
            "Automatyka",
            "Robotyka",
            "Biotechnologia",
            "Budownictwo",
            "Cyberbezpieczeństwo",
            "Ekonomia",
            "Elektromobilność",
            "Elektronika",
            "Telekomunikacja",
            "Elektrotechnika",
            "Energetyka",
            "Fizyka techniczna",
            "Geodezja i kartografia",
            "Geoinformatyka",
            "Gospodarka przestrzenna",
            "Informatyka",
            "Systemy informacyjne",
            "Inżynieria biomedyczna",
            "Inżynieria chemiczna i procesowa",
            "Inżynieria i analiza danych",
            "Inżynieria Internetu rzeczy",
            "Inżynieria materiałowa",
            "Inżynieria mechaniczna",
            "Inżynieria pojazdów elektrycznych i hybrydowych",
            "Inżynieria środowiska",
            "Inżynieria zarządzania",
            "Lotnictwo i kosmonautyka",
            "Matematyka",
            "Matematyka i analiza danych",
            "Mechanika i budowa maszyn",
            "Mechanika i projektowanie maszyn",
            "Mechatronika",
            "Mechatronika pojazdów i maszyn roboczych",
            "Ochrona środowiska",
            "Papiernictwo i poligrafia",
            "Przemysłowe zastosowania informatyki",
            "Technologia chemiczna",
            "Telekomunikacja",
            "Transport",
            "Zarządzanie",
            "Zarządzanie i inżynieria produkcji"
    );

    private List<Integer> freeBoothNumbers;

    @Autowired
    public SetCompanyFormController(CompanyRepository companyRepository, BoothRepository boothRepository) {
        this.companyRepository = companyRepository;
        this.boothRepository = boothRepository;
    }

    @GetMapping("/addCompany")
    public String adminPage(Model model) {
        calculateFreeBoothNumbers();

        model.addAttribute("pageTitle", "Dodaj nową firmę");
        model.addAttribute("actionUrl", "/adminView/addCompany");
        model.addAttribute("buttonText", "Dodaj firmę");
        model.addAttribute("company", new Company());
        model.addAttribute("freeBoothNumbers", freeBoothNumbers);
        model.addAttribute("availableJobTopics", availableJobTopics);
        return "adminview/setCompanyForm";
    }

    @PostMapping("/addCompany")
    public String addCompany(@ModelAttribute Company company, BindingResult result, Model model) {
        if (companyRepository.findByName(company.getName()) != null) {
            result.rejectValue("name", "error.company", "Nazwa firmy jest już zajęta");
        }
        if (companyRepository.findByWebsiteUrl(company.getWebsiteUrl()) != null) {
            result.rejectValue("websiteUrl", "error.company", "Podany adres strony internetowej jest już wykorzystywany przez inną firmę");
        }

        if (company.getJobTopics().isEmpty()) {
            result.rejectValue("jobTopics", "error.company", "Musisz wybrać co najmniej jeden kierunek");
        }

        if (result.hasErrors()) {
            model.addAttribute("pageTitle", "Dodaj nową firmę");
            model.addAttribute("actionUrl", "/adminView/addCompany");
            model.addAttribute("buttonText", "Dodaj firmę");
            model.addAttribute("company", company);
            model.addAttribute("freeBoothNumbers", freeBoothNumbers);
            model.addAttribute("availableJobTopics", availableJobTopics);
            return "adminview/setCompanyForm";
        }

        companyRepository.save(company);
        return "redirect:/adminView";
    }

    private void calculateFreeBoothNumbers() {
        List<Integer> takenBoothNumbers = companyRepository.findAll()
                .stream()
                .map(Company::getBoothNumber)
                .toList();

        int totalBooths = (int) boothRepository.count();

        freeBoothNumbers = IntStream.rangeClosed(1, totalBooths)
                .boxed()
                .filter(boothNumber -> !takenBoothNumbers.contains(boothNumber))
                .collect(Collectors.toList());
    }

    @GetMapping("/editCompany/{id}")
    public String editCompanyForm(@PathVariable("id") Long id, Model model) {
        Company company = companyRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid company Id:" + id));

        calculateFreeBoothNumbers();
        freeBoothNumbers.add(company.getBoothNumber());
        Collections.sort(freeBoothNumbers);

        model.addAttribute("company", company);
        model.addAttribute("pageTitle", "Edytuj firmę");
        model.addAttribute("actionUrl", "/adminView/editCompany/" + id + "/confirm");
        model.addAttribute("buttonText", "Aktualizuj dane firmy");
        model.addAttribute("freeBoothNumbers", freeBoothNumbers);
        model.addAttribute("availableJobTopics", availableJobTopics);
        return "adminview/setCompanyForm";
    }

    @PostMapping("/editCompany/{id}/confirm")
    public String saveCompany(@PathVariable("id") Long id, @ModelAttribute("company") Company company, BindingResult result, Model model) {
        Company foundCompany = companyRepository.findByName(company.getName());
        if (foundCompany != null) {
            if (!Objects.equals(foundCompany.getId(), company.getId())) {
                result.rejectValue("name", "error.company", "Nazwa firmy jest już zajęta");
            }
        }

        foundCompany = companyRepository.findByWebsiteUrl(company.getWebsiteUrl());
        if (foundCompany != null) {
            if (!Objects.equals(foundCompany.getId(), company.getId())) {
                result.rejectValue("websiteUrl", "error.company", "Podany adres strony internetowej jest już wykorzystywany przez inną firmę");
            }
        }

        if (company.getJobTopics().isEmpty()) {
            result.rejectValue("jobTopics", "error.company", "Musisz wybrać co najmniej jeden kierunek");
        }

        if (result.hasErrors()) {
            model.addAttribute("pageTitle", "Edytuj firmę");
            model.addAttribute("actionUrl", "/adminView/editCompany/" + id + "/confirm");
            model.addAttribute("buttonText", "Aktualizuj dane firmy");
            model.addAttribute("company", company);
            model.addAttribute("freeBoothNumbers", freeBoothNumbers);
            model.addAttribute("availableJobTopics", availableJobTopics);
            return "adminview/setCompanyForm";
        }

        companyRepository.save(company);
        return "redirect:/adminView";
    }
}
