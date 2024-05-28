package com.group06.JobFairApp.controller;

import com.group06.JobFairApp.model.Workshop;
import com.group06.JobFairApp.repository.WorkshopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("adminView/addWorkshop")
public class AddWorkshopController {

    private final WorkshopRepository workshopRepository;

    @Autowired
    public AddWorkshopController(WorkshopRepository workshopRepository) {
        this.workshopRepository = workshopRepository;
    }

    @GetMapping
    private String adminPage(Model model) {
        model.addAttribute("workshop", new Workshop());
        return "adminview/addWorkshop";
    }

    @PostMapping
    private String addWorkshop(@ModelAttribute Workshop workshop, BindingResult result, Model model) {
        List<Workshop> workshopsInPlace = workshopRepository.findByPlace(workshop.getPlace());

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime workshopStart = LocalDateTime.parse(workshop.getDate() + " " + workshop.getTime(), dateFormatter);
        LocalDateTime workshopEnd = workshopStart.plusMinutes(workshop.getDuration());

        boolean hasConflict = workshopsInPlace.stream()
                .anyMatch(w -> {
                    LocalDateTime existingWorkshopStart = LocalDateTime.parse(w.getDate() + " " + w.getTime(), dateFormatter);
                    LocalDateTime existingWorkshopEnd = existingWorkshopStart.plusMinutes(w.getDuration());
                    return workshopStart.isBefore(existingWorkshopEnd) && workshopEnd.isAfter(existingWorkshopStart);
                });

        if (hasConflict) {
            List<String> conflictingIntervals = workshopsInPlace.stream()
                    .map(w -> {
                        LocalDateTime existingWorkshopStart = LocalDateTime.parse(w.getDate() + " " + w.getTime(), dateFormatter);
                        LocalDateTime existingWorkshopEnd = existingWorkshopStart.plusMinutes(w.getDuration());
                        return existingWorkshopStart.format(dateFormatter) + " - " + existingWorkshopEnd.format(dateFormatter);
                    })
                    .sorted()
                    .collect(Collectors.toList());

            result.rejectValue("place", "error.workshop", "Podana sala jest zajęta w godzinach:\n" +
                    String.join("\n", conflictingIntervals));
        }

        int startTimeMinutes = Integer.parseInt(workshop.getTime().split(":")[1]);
        if (startTimeMinutes % 5 != 0) {
            result.addError(new FieldError("workshop", "time", "Godzina rozpoczęcia warsztatów może być określana ze skokiem 5 minut"));
        }

        if (result.hasErrors()) {
            return "adminview/addWorkshop";
        }

        workshopRepository.save(workshop);
        return "redirect:/adminView";
    }
}
