package com.group06.JobFairApp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Booth {

    @Id
    @GeneratedValue
    private int id;
    private String companyName;

    public Booth(int boothId, String companyName) {
        this.companyName = companyName;
        this.id = boothId;
    }

    public Booth(int boothId) {
        this.companyName = "Puste stoisko";
        this.id = boothId;
    }
}
