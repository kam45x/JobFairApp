package com.group06.JobFairApp.service;

import com.group06.JobFairApp.model.Company;
import com.group06.JobFairApp.model.Users;
import com.group06.JobFairApp.repository.CompanyRepository;
import com.group06.JobFairApp.repository.UsersRepository;
import com.group06.JobFairApp.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class FavoriteService {
    private final CompanyRepository companyRepository;
    private final UsersRepository usersRepository;
    private final AuthenticationService authenticationService;

    @Autowired
    public FavoriteService(CompanyRepository companyRepository,
                           UsersRepository usersRepository, AuthenticationService authenticationService) {
        this.companyRepository = companyRepository;
        this.usersRepository = usersRepository;
        this.authenticationService = authenticationService;
    }

    public void addFavoriteToAuthenticatedUser(Long companyId) {
        Users user = authenticationService.getAuthenticatedUser();
        if (user != null) {
            Company company = companyRepository.findById(companyId).orElse(null);
            if (company != null) {
                user.addFavoriteCompany(company);
                usersRepository.save(user);
            }
        }
    }

    public void removeFavoriteFromAuthenticatedUser(Long companyId) {
        Users user = authenticationService.getAuthenticatedUser();
        if (user != null) {
            Company company = companyRepository.findById(companyId).orElse(null);
            if (company != null) {
                user.removeFavoriteCompany(company);
                usersRepository.save(user);
            }
        }
    }
}
