package com.group06.JobFairApp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
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
    private String websiteUrl;

    @Column(columnDefinition = "TEXT")
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

    public boolean matchesJobTopics(List<String> jobTopics) {
        if (jobTopics == null)
        {
            return false;
        }

        String[] companyjobTopics = this.jobTopics.split(", ");

        for (String topic : companyjobTopics)
        {
            if (jobTopics.contains(topic))
            {
                return true;
            }
        }

        return false;
    }
}
