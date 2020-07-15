package com.example.demo.Services;

import java.util.List;

import javax.transaction.Transactional;


import com.example.demo.Models.Player;
import com.example.demo.Repositories.PlayerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PlayerService {
    @Autowired
    private PlayerRepository repo;
     
    public List<Player> listAll() {
        return repo.findAll();
    }
     
    public Player save(Player entity) {
        return repo.save(entity);
    }
     
    public Player get(long id) {
        return repo.findById(id).get();
    }
     
    public void delete(long id) {
        repo.deleteById(id);
    }
}
