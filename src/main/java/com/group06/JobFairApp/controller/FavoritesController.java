package com.group06.JobFairApp.controller;

import com.group06.JobFairApp.model.Company;
import com.group06.JobFairApp.model.Users;
import com.group06.JobFairApp.repository.CompanyRepository;
import com.group06.JobFairApp.repository.UsersRepository;
import com.group06.JobFairApp.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/favorites")
public class FavoritesController {

    private final AuthenticationService authenticationService;
    private final CompanyRepository companyRepository;
    private final UsersRepository usersRepository;

    @Autowired
    public FavoritesController(AuthenticationService authenticationService,
                               CompanyRepository companyRepository, UsersRepository usersRepository) {
        this.authenticationService = authenticationService;
        this.companyRepository = companyRepository;
        this.usersRepository = usersRepository;
    }

    @PostMapping("/add/{companyId}")
    public String addFavorite(@PathVariable Long companyId) {
        Users user = authenticationService.getAuthenticatedUser();
        if (user != null) {
            Company company = companyRepository.findById(companyId).orElse(null);
            if (company != null) {
                user.addFavoriteCompany(company);
                usersRepository.save(user);
            }
        }
        return "redirect:/company/page/" + companyId;
    }

    @PostMapping("/remove/{companyId}")
    public String removeFavorite(@PathVariable Long companyId) {
        Users user = authenticationService.getAuthenticatedUser();
        if (user != null) {
            Company company = companyRepository.findById(companyId).orElse(null);
            if (company != null) {
                user.removeFavoriteCompany(company);
                usersRepository.save(user);
            }
        }
        return "redirect:/company/page/" + companyId;
    }
}
