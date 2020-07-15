package com.example.demo.Controllers;

import java.util.List;

import com.example.demo.Services.StatService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path="statistics")
public class StatisticsController {

    @Autowired
    StatService statSvc;
    @GetMapping("")
    public String getStatistics(Model model){
        model.addAttribute("top5", statSvc.getTop5("point"));
        model.addAttribute("bottom5", statSvc.getButtomFive("point"));
        model.addAttribute("top5rebound", statSvc.getTop5("rebound"));
        model.addAttribute("bottom5rebound", statSvc.getButtomFive("rebound"));
        return "statistics";
    }
}