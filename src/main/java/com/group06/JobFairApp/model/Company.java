package com.group06.JobFairApp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Company {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String logoUrl;
    private String imgUrl;
    private String jobTopics;
    private int boothId;
    private int boothNumber;
    private String websiteUrl;

    @Column(columnDefinition = "TEXT")
    private String skills;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "TEXT")
    private String whoLookingFor;

    @Column(columnDefinition = "TEXT")
    private String longDescription;

    public Company(String name, String logoUrl, String imgUrl, String jobTopics,
                   int boothNumber, String websiteUrl, String description,
                   String who_looking_for, String longDescription, String skills) {
        this.name = name;
        this.logoUrl = logoUrl;
        this.imgUrl = imgUrl;
        this.jobTopics = jobTopics;
        this.boothNumber = boothNumber;
        this.websiteUrl = websiteUrl;
        this.description = description;
        this.whoLookingFor = who_looking_for;
        this.longDescription = longDescription;
        this.skills = skills;
    }

    public boolean shouldBeShowed(List<String> filters, String requestedName, boolean showAll) {
        if (showAll) {
            return true;
        }
        if (filters == null) {
            return false;
        }

        // Check if matches requested name of company
        if (!requestedName.isEmpty()) {
            if (!this.name.toLowerCase().contains(requestedName.toLowerCase())) {
                return false;
            }
        }

        // Check if matches job topics
        String[] companyjobTopics = this.jobTopics.split(", ");
        for (String topic : companyjobTopics) {
            if (filters.contains("F1 " + topic)) {
                return true;
            }
        }

        return false;
    }

    public double matchedSkills(List<String> filters) {
        if (filters == null) {
            return 0.0;
        }

        String[] companySkills = this.skills.split(", ");
        int matchedSkills = 0;
        int allSkills = companySkills.length;
        for (String skill : companySkills) {
            if (filters.contains("F2 " + skill)) {
                matchedSkills++;
            }
        }

        return ((double) matchedSkills / allSkills);
    }
}
