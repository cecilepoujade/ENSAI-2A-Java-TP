package fr.ensai.running.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fr.ensai.running.model.Athlete;
import fr.ensai.running.service.AthleteService;

@Controller
@RequestMapping("/athlete")
public class AthleteController {

    @Autowired
    private AthleteService athleteService;

    @GetMapping()
    public String findAllAthletes(Model model) {
        model.addAttribute("athletes", athleteService.findAll());
        return "allAthletes";
    }

    @GetMapping("/addNew")
    public String addNewAthlete(Model model) {
        Athlete athlete = new Athlete();
        model.addAttribute("athlete", athlete);
        return "createAthlete";
    }

    @PostMapping("/save")
    public String saveAthlete(@ModelAttribute("athlete") Athlete athlete) {
        athleteService.save(athlete);
        return "redirect:/athlete";
    }

    @GetMapping("/delete/{id}")
    public String deleteAthleteById(@PathVariable(value = "id") long id, RedirectAttributes redirectAttributes) {
        athleteService.deleteById(id);
        return "redirect:/athlete";

    }
}