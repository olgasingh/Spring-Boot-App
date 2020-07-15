package com.example.demo.Controllers;

import java.util.List;

import javax.validation.Valid;

import com.example.demo.Models.Team;
import com.example.demo.Models.Coach;

import com.example.demo.Services.CoachService;
import com.example.demo.Services.TeamService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequestMapping(path = "/coaches")
public class CoachController {

    @Autowired
    private CoachService coachSrv;

    @Autowired
    private TeamService teamSrv;

    @GetMapping(value = "")
    public String list(Model model) {
        List<Coach> coaches = coachSrv.listAll();
        model.addAttribute("coaches", coaches);
        return "coaches";
    }

    @PostMapping(value = "/save")
    public String save(@Valid @ModelAttribute Coach coach, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "coach";
        }
        coachSrv.save(coach);

        return "redirect:/coaches";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable(name = "id") int id) {
        coachSrv.delete(id);
        return "redirect:/coaches";
    }

    @GetMapping("/{id}")
    public String edit(Model model, @PathVariable(name = "id") int id) {
        Coach entity = null;
        if (id == 0) {
            entity = new Coach();
        } else {

            entity = coachSrv.get(id);
        }
        model.addAttribute("entity", entity);
        List<Team> teams = teamSrv.listAll();
        model.addAttribute("teams", teams);

        return "coach";
    }
}