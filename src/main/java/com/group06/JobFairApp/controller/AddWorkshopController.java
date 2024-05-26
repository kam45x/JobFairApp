package com.group06.JobFairApp.controller;

import com.group06.JobFairApp.model.Workshop;
import com.group06.JobFairApp.repository.WorkshopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/addWorkshop")
public class AddWorkshopController {

    private final WorkshopRepository workshopRepository;

    @Autowired
    public AddWorkshopController(WorkshopRepository workshopRepository) {
        this.workshopRepository = workshopRepository;
    }

    @GetMapping
    private String adminPage()
    {
        return "adminview/addWorkshop";
    }

    @PostMapping
    private String addCompany(Workshop workshop)
    {
        workshopRepository.save(workshop);
        return "redirect:/";
    }
}

