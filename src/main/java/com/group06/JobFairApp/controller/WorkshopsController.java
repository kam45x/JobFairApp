package com.group06.JobFairApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/workshops")
public class WorkshopsController {

    public WorkshopsController()
    {
    }

    @GetMapping("/page")
    public String showWorkshops(Model model)
    {
        return "workshops";
    }
}
