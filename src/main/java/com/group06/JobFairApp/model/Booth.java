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
    private Long id;
    private int boothNumber;
    private String companyName;
    private String color;

    public Booth(int boothNumber, String companyName, String color) {
        this.boothNumber = boothNumber;
        this.companyName = companyName;
        this.color = color;
    }

    public Booth(int boothNumber, String companyName) {
        this(boothNumber, companyName, "#ddd");
    }

    public Booth(int boothNumber) {
        this(boothNumber, "Puste stoisko", "#ddd");
    }

    public void setColorByName(String colorName) {
        switch (colorName) {
            case "green1" :
                this.color = "#98FB98";
                break;
            case "green2":
                this.color = "#3CB371";
                break;
            case "green3":
                this.color = "#008000";
                break;
            case "gray":
                this.color = "#ddd";
                break;
            case "dimGray":
                this.color = "#696969";
                break;
        }
    }
}
