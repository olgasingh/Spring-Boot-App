package com.example.demo.Controllers;

import java.util.List;

import javax.validation.Valid;

import com.example.demo.Models.Game;
import com.example.demo.Models.Player;
import com.example.demo.Models.Stat;
import com.example.demo.Services.CoachService;
import com.example.demo.Services.GameService;
import com.example.demo.Services.PlayerService;
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
@RequestMapping(path = "games")
public class GameController {

    @Autowired
    GameService gameSvc;
    @Autowired
    PlayerService playerSrv;
    @Autowired
    TeamService teamSrv;

    @Autowired 
    CoachService coachSrv;

    @GetMapping("")
    public String list(Model model){
        model.addAttribute("games", gameSvc.listAll());
        return "games";
    }

    @GetMapping("/{id}")
    public String edit(Model model,@PathVariable(name = "id") int id) {
        Game entity = null;
        if (id == 0) {
            entity = new Game();
        } else {

            entity = gameSvc.get(id);
        }
        List<Player> players = playerSrv.listAll();
        List<Stat> stats = entity.getStats();
        players.forEach(p->{
            Boolean found = false;
            for (Stat stat : stats) {
                if(stat.getPlayer().getId().equals(p.getId())){
                    found=true;
                    break;
                }
            }
            if(!found){
                Stat st = new Stat();
                st.setPlayer(p);
                stats.add(st);
            }
        });
       /* entity.getStats().forEach((stat) -> {
            players.stream().filter(p-> p.getId().equals(stat.getPlayer().getId()));
        });*/
        
        model.addAttribute("entity", entity);
        //model.addAttribute("players", players);
        model.addAttribute("teams", teamSrv.listAll());
        model.addAttribute("coaches", coachSrv.listAll());

        return "game";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute Game game, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "game";
        }


        try {
            gameSvc.save(game);
          } catch (DataIntegrityViolationException  e) {
            bindingResult.rejectValue("name", "nameInUse", "name is already in use");
      
            return "game";
          }
        

        return "redirect:/games";
    }


    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") int id) {
        gameSvc.delete(id);
        return "redirect:/games";
   }
}