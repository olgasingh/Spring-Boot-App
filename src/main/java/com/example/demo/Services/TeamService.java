package com.example.demo.Services;

import java.util.List;

import javax.transaction.Transactional;


import com.example.demo.Models.Team;
import com.example.demo.Repositories.TeamRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class TeamService {
    @Autowired
    private TeamRepository repo;
     
    public List<Team> listAll() {
        return repo.findAll();
    }
     
    public Team save(Team entity) {
        return repo.save(entity);
    }
     
    public Team get(long id) {
        return repo.findById(id).get();
    }
     
    public void delete(long id) {
        repo.deleteById(id);
    }
}
