package com.example.demo.Services;

import java.util.List;

import javax.transaction.Transactional;


import com.example.demo.Models.Role;
import com.example.demo.Repositories.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class RoleService {
    @Autowired
    private RoleRepository repo;
     
    public List<Role> listAll() {
        return repo.findAll();
    }
     
    public Role save(Role role) {
        return repo.save(role);
    }
     
    public Role get(long id) {
        return repo.findById(id).get();
    }
     
    public void delete(long id) {
        repo.deleteById(id);
    }
}
