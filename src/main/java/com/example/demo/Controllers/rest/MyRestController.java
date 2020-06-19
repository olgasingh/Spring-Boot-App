package com.example.demo.Controllers.rest;

import java.util.List;

import com.example.demo.Models.Role;
import com.example.demo.Services.RoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyRestController {

    @Autowired 
    RoleService rs;
    
    @RequestMapping("/api/roles")
    public List<Role> getRoles(){
        return rs.listAll();
    }
    
}