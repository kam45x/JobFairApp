package com.group06.JobFairApp.service;

import com.group06.JobFairApp.model.Users;
import com.group06.JobFairApp.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final UsersRepository usersRepository;

    @Autowired
    public AuthenticationService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public Users getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() &&
                authentication.getPrincipal() instanceof UserDetails) {
            String email = ((UserDetails) authentication.getPrincipal()).getUsername();
            return usersRepository.findByEmail(email);
        }
        return null;
    }

    public boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && authentication.isAuthenticated() &&
                authentication.getPrincipal() instanceof UserDetails;
    }
}
