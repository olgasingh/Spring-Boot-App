package com.example.demo.Controllers;

import java.util.List;

import com.example.demo.Models.Module;
import com.example.demo.Models.Role;
import com.example.demo.Services.ModuleService;
import com.example.demo.Services.RoleService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(path = "/modules")
public class ModuleController {
    @Autowired
    private ModuleService msv;
    @Autowired
    private RoleService rsv;

    @RequestMapping(path = "")
    public String getModules(Model model) {
        List<Module> listModules = msv.listAll();
        model.addAttribute("listModules", listModules);
        return "modules"; 
    } 
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveModule(@ModelAttribute("module") Module module) {
        msv.save(module);

        return "redirect:/modules";
    }

    @RequestMapping("/delete/{id}")
    public String deleteModule(@PathVariable(name = "id") int id) {
        msv.delete(id);
        return "redirect:/modules";
    }

    @RequestMapping("/{id}")
    public String showEditRolePage(Model model, @PathVariable(name = "id") int id) {
        Module module = null;
        if (id == 0) {
            module = new Module();
        } else {
         module = msv.get(id);
        }
        model.addAttribute("module", module);
        List<Role> listRoles = rsv.listAll();
        model.addAttribute("listRoles", listRoles);

        return "module";
    }
}