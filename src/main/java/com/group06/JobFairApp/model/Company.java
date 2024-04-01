package com.group06.JobFairApp.model;

import jakarta.persistence.Column;
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
public class Company {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String imgUrl;
    private String jobTopics;
    private int boothId;
    private String websiteUrl;

    @Column(columnDefinition = "TEXT")
    private String description;

    public Company(String name, String imgUrl, String jobTopics, int boothId, String websiteUrl, String description) {
        this.name = name;
        this.imgUrl = imgUrl;
        this.jobTopics = jobTopics;
        this.boothId = boothId;
        this.websiteUrl = websiteUrl;
        this.description = description;
    }
}
