package com.example.demo.Controllers.rest;

import java.util.List;

import com.example.demo.Models.Role;
import com.example.demo.Services.RoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//@CrossOrigin(maxAge = 4800, allowCredentials = "false")
@RestController
//@CrossOrigin(origins = "*", allowedHeaders = "*")

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping("/api/roles")
public class MyRestController {

    @Autowired 
    RoleService rs;
    
    @GetMapping()
    public List<Role> getRoles(){
        return rs.listAll();
    }

    @PostMapping()
    public Role addRole(@RequestBody Role role){
        Role r = rs.save(role);
        return r;
    }
    @CrossOrigin
   // @DeleteMapping("/{id}")

    @RequestMapping( path = "{id}", method = RequestMethod.DELETE)
    public void deleteRole(@PathVariable("id") Long id){
        rs.delete(id);
    }
    
}