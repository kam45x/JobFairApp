package com.group06.JobFairApp.service;

import com.group06.JobFairApp.model.Company;
import com.group06.JobFairApp.model.Users;
import com.group06.JobFairApp.repository.CompanyRepository;
import com.group06.JobFairApp.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FavoriteService {

    private final CompanyRepository companyRepository;
    private final UsersRepository usersRepository;
    private final AuthenticationService authenticationService;

    @Autowired
    public FavoriteService(CompanyRepository companyRepository,
                           UsersRepository usersRepository,
                           AuthenticationService authenticationService) {
        this.companyRepository = companyRepository;
        this.usersRepository = usersRepository;
        this.authenticationService = authenticationService;
    }

    public void addFavoriteToAuthenticatedUser(Long companyId) {
        Users user = authenticationService.getAuthenticatedUser();
        if (user != null && user.getRoles().equals("ROLE_USER")) {
            Company company = companyRepository.findById(companyId).orElse(null);
            if (company != null && !user.getFavoritesCompaniesIdList().contains(companyId)) {
                user.addFavoriteCompanyById(companyId);
                usersRepository.save(user);
            }
        }
    }

    public void removeFavoriteFromAuthenticatedUser(Long companyId) {
        Users user = authenticationService.getAuthenticatedUser();
        if (user != null) {
            user.removeFavoriteCompanyById(companyId);
            usersRepository.save(user);
        }
    }

    public List<Company> getFavoriteCompaniesListFromAuthenticatedUser() {
        Users user = authenticationService.getAuthenticatedUser();
        if (user != null) {
            List<Long> favoritesCompaniesIdList = user.getFavoritesCompaniesIdList();
            List<Company> favoriteCompanies = new ArrayList<>();
            for (Long id : favoritesCompaniesIdList) {
                Company company = companyRepository.findById(id).orElse(null);
                if (company != null) {
                    favoriteCompanies.add(company);
                } else {
                    user.removeFavoriteCompanyById(id);
                    usersRepository.save(user);
                }
            }
            return favoriteCompanies;
        }
        return new ArrayList<>();
    }
}
