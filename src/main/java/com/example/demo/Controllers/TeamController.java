package com.example.demo.Controllers;

import javax.validation.Valid;

import com.example.demo.Models.Team;
import com.example.demo.Services.TeamService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(path = "teams")
public class TeamController {

    @Autowired
    TeamService teamSvc;

    @GetMapping("")
    public String list(Model model){
        model.addAttribute("teams", teamSvc.listAll());
        return "teams";
    }

    @GetMapping("/{id}")
    public String edit(Model model,@PathVariable(name = "id") int id) {
        Team team = null;
        if (id == 0) {
            team = new Team();
        } else {

        team = teamSvc.get(id);
        }
        model.addAttribute("team", team);

        return "team";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute Team team, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "team";
        }


        try {
            teamSvc.save(team);
          } catch (DataIntegrityViolationException  e) {
            bindingResult.rejectValue("name", "nameInUse", "name is already in use");
      
            return "team";
          }
        

        return "redirect:/teams";
    }


    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") int id) {
        teamSvc.delete(id);
        return "redirect:/teams";
   }
}