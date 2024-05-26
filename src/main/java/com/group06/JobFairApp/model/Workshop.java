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
public class Workshop {

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String date;
    private String time;
    private String place;
    private String host;

    public Workshop(String title, String date, String time, String place, String host) {
        this.title = title;
        this.date = date;
        this.time = time;
        this.place = place;
        this.host = host;
    }
}
