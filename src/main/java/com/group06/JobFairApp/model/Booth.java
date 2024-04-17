package com.group06.JobFairApp.model;

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
public class Booth {

    @Id // Oznaczenie pola jako klucz główny w encji
    @GeneratedValue // Oznaczenie, że wartość tego pola będzie generowana automatycznie
    private int id; // identyfikator stoiska
    private String companyName; // nazwa firmy

    // Konstruktor dla stoiska obsadzonego daną firmą
    public Booth(int boothId, String companyName) {
        this.companyName = companyName;
        this.id = boothId;
    }

    // Konstruktor dla stanowiska pustego
    public Booth(int boothId) {
        this.companyName = "Puste stoisko";
        this.id = boothId;
    }
}
