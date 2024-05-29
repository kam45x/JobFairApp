package com.group06.JobFairApp.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Users implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    private String password;
    private String name;
    private String surname;
    private String phoneNumber;
    private String roles;

    @Column(columnDefinition = "TEXT")
    private String favoritesCompaniesIdString = "";

    public Users(String email, String password, String name, String surname, String phoneNumber, String roles) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.roles = roles.startsWith("ROLE_") ? roles : "ROLE_" + roles;
        this.favoritesCompaniesIdString = "";
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(roles));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void addFavoriteCompanyById(Long companyId) {
        if (favoritesCompaniesIdString == null || favoritesCompaniesIdString.isEmpty()) {
            favoritesCompaniesIdString = companyId.toString();
        } else {
            favoritesCompaniesIdString += "," + companyId.toString();
        }
    }

    public void removeFavoriteCompanyById(Long companyId) {
        if (favoritesCompaniesIdString != null && !favoritesCompaniesIdString.isEmpty()) {
            String companyIdString = String.valueOf(companyId);
            String[] parts = favoritesCompaniesIdString.split(",");
            StringBuilder temporaryFavoritesCompaniesIDs = new StringBuilder();

            for (String part : parts) {
                if (!part.equals(companyIdString)) {
                    if (!temporaryFavoritesCompaniesIDs.isEmpty()) {
                        temporaryFavoritesCompaniesIDs.append(",");
                    }
                    temporaryFavoritesCompaniesIDs.append(part);
                }
            }

            favoritesCompaniesIdString = temporaryFavoritesCompaniesIDs.toString();
        }
    }

    public List<Long> getFavoritesCompaniesIdList() {
        List<Long> favoritesCompaniesIds = new ArrayList<>();

        if (favoritesCompaniesIdString != null && !favoritesCompaniesIdString.isEmpty()) {
            String[] parts = favoritesCompaniesIdString.split(",");

            for (String part : parts) {
                favoritesCompaniesIds.add(Long.parseLong(part.trim()));
            }
        }

        return favoritesCompaniesIds;
    }
}
