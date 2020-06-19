
package com.example.demo.Controllers;

import java.util.List;

import com.example.demo.Models.Role;
import com.example.demo.Models.User;
import com.example.demo.Services.RoleService;
import com.example.demo.Services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

    @Autowired
    private UserService ursv;

    @Autowired
    private RoleService rsv;

    @RequestMapping(path = "/users")
    public String getUsers(Model model) {
        List<User> listUsers = ursv.listAll();
        model.addAttribute("listUsers", listUsers);
        return "users";
    }

    @RequestMapping(value = "/users/save", method = RequestMethod.POST)
    public String saveUser(@ModelAttribute("user") User user) {

        ursv.save(user);

        return "redirect:/users";
    }

    @RequestMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable(name = "id") int id) {
        ursv.delete(id);
        return "redirect:/users";
    }

    @RequestMapping("/users/{id}")
    public String showEditUserPage(Model model, @PathVariable(name = "id") int id) {
        User user = null;
        if(id==0){
            user=new User();
        }else{
         user = ursv.get(id);
        }
        model.addAttribute("user", user);
        List<Role> listRoles = rsv.listAll();
        model.addAttribute("listRoles", listRoles);

        return "user";
    }

}