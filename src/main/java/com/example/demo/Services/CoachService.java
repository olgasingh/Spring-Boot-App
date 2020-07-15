package com.example.demo.Services;

import java.util.List;

import javax.transaction.Transactional;


import com.example.demo.Models.Coach;
import com.example.demo.Repositories.CoachRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CoachService {
    @Autowired
    private CoachRepository repo;
     
    public List<Coach> listAll() {
        return repo.findAll();
    }
     
    public Coach save(Coach entity) {
        return repo.save(entity);
    }
     
    public Coach get(long id) {
        return repo.findById(id).get();
    }
     
    public void delete(long id) {
        repo.deleteById(id);
    }
}
