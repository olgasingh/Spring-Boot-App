package com.example.demo.Controllers;

import java.util.List;

import javax.validation.Valid;

import com.example.demo.Models.Player;
//import com.example.demo.Models.Coach;

import com.example.demo.Services.PlayerService;
//import com.example.demo.Services.TeamService;

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
@RequestMapping(path = "/players")
public class PlayerController {

    @Autowired
    private PlayerService playerSrv;

    //@Autowired
    //private TeamService teamSrv;

    @GetMapping(value = "")
    public String list(Model model) {
        List<Player> players = playerSrv.listAll();
        model.addAttribute("players", players);
        return "players";
    }

    @PostMapping(value = "/save")
    public String save(@Valid @ModelAttribute Player player, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "players";
        }
        playerSrv.save(player);

        return "redirect:/players";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable(name = "id") int id) {
        playerSrv.delete(id);
        return "redirect:/players";
    }

    @GetMapping("/{id}")
    public String edit(Model model, @PathVariable(name = "id") int id) {
        Player entity = null;
        if (id == 0) {
            entity = new Player();
        } else {

            entity = playerSrv.get(id);
        }
        model.addAttribute("entity", entity);

        return "player";
    }
}