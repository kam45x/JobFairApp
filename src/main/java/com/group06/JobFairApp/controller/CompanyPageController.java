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

    // Konstruktor kontrolera CompanyPageController
    @Autowired
    public CompanyPageController(CompanyRepository companyRepository)
    {
        this.companyRepository = companyRepository;
    }

    // Metoda obsługująca żądanie GET dla ścieżki "/company/page/{id}"
    @GetMapping("/page/{id}")
    public String showCompanyPage(@PathVariable("id") Long id, Model model)
    {
        // Dodanie atrybutu "page_company" do modelu, aby był dostępny w widoku
        model.addAttribute("page_company", companyRepository.findById(id));
        // Zwrócenie nazwy widoku "company_page", który zostanie wyrenderowany przez silnik szablonów
        return "company_page";
    }
}
