package com.group06.JobFairApp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor // Adnotacja Lombok tworząca domyślny konstruktor bezargumentowy
@Getter // Adnotacja Lombok tworząca getter-y dla wszystkich pól klasy
@Setter // Adnotacja Lombok tworząca setter-y dla wszystkich pól klasy
@Entity // Adnotacja oznaczająca, że klasa jest encją JPA
public class Company {

    @Id // Oznaczenie pola jako klucz główny w encji
    @GeneratedValue // Wartość klucza jest generowana domyślnie
    private Long id;
    private String name;
    private String logoUrl;
    private String imgUrl;
    private String jobTopics;
    private int boothId;
    private String websiteUrl;

    @Column(columnDefinition = "TEXT") // Oznaczenie, że pole będzie mapowane na typ TEXT w bazie danych
    private String description;

    @Column(columnDefinition = "TEXT")
    private String whoLookingFor;

    @Column(columnDefinition = "TEXT")
    private String longDescription;

    public Company(String name, String logoUrl, String imgUrl, String jobTopics,
                   int boothId, String websiteUrl, String description,
                   String who_looking_for, String longDescription) {
        this.name = name;
        this.logoUrl = logoUrl;
        this.imgUrl = imgUrl;
        this.jobTopics = jobTopics;
        this.boothId = boothId;
        this.websiteUrl = websiteUrl;
        this.description = description;
        this.whoLookingFor = who_looking_for;
        this.longDescription = longDescription;
    }
}
