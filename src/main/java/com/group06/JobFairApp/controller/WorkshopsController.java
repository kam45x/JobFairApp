package com.group06.JobFairApp.controller;

import com.group06.JobFairApp.repository.WorkshopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/workshops")
public class WorkshopsController {

    private final WorkshopRepository workshopRepository;

    @Autowired
    public WorkshopsController(WorkshopRepository workshopRepository) {
        this.workshopRepository = workshopRepository;
    }

    @GetMapping("/page")
    public String showWorkshops(Model model) {
        model.addAttribute("workshops", workshopRepository.findAll());

        return "workshops";
    }
}
