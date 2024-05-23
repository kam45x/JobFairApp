package com.group06.JobFairApp.model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;
    private String name;
    private String surname;
    private String phoneNumber;

    private String roles;

    public Users(String email, String password, String name, String surname, String phoneNumber, String roles) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.roles = roles;
    }
}

