package com.group06.JobFairApp.controller;

import com.group06.JobFairApp.model.Company;
import com.group06.JobFairApp.model.Users;
import com.group06.JobFairApp.service.AuthenticationService;
import com.group06.JobFairApp.repository.CompanyRepository;
import com.group06.JobFairApp.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/company")
public class CompanyPageController {

    private final CompanyRepository companyRepository;
    private final AuthenticationService authenticationService;
    private final FavoriteService favoriteService;

    @Autowired
    public CompanyPageController(CompanyRepository companyRepository,
                                 AuthenticationService authenticationService,
                                 FavoriteService favoriteService) {
        this.companyRepository = companyRepository;
        this.authenticationService = authenticationService;
        this.favoriteService = favoriteService;
    }

    @GetMapping("/page/{id}")
    public String showCompanyPage(@PathVariable("id") Long id, Model model) {
        Company company = companyRepository.findById(id).orElse(null);
        if (company != null) {
            model.addAttribute("page_company", company);

            Users user = authenticationService.getAuthenticatedUser();
            if (user != null) {
                model.addAttribute("authenticated", true);
                model.addAttribute("isAdmin", user.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN")));
                model.addAttribute("isFavorite", user.getFavoriteCompanies().contains(company));
            } else {
                model.addAttribute("authenticated", false);
            }
        } else {
            return "redirect:/";
        }
        return "companyPage";
    }

    @PostMapping("/page/{companyId}/addToFavorite")
    public String addFavorite(@PathVariable Long companyId) {
        favoriteService.addFavoriteToAuthenticatedUser(companyId);
        return "redirect:/company/page/" + companyId;
    }

    @PostMapping("/page/{companyId}/removeFromFavorite")
    public String removeFavorite(@PathVariable Long companyId) {
        favoriteService.removeFavoriteFromAuthenticatedUser(companyId);
        return "redirect:/company/page/" + companyId;
    }
}
