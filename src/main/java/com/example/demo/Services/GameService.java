package com.example.demo.Services;

import java.util.List;

import javax.transaction.Transactional;

import com.example.demo.Models.Game;
import com.example.demo.Models.Stat;
import com.example.demo.Repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class GameService {
    @Autowired
    private GameRepository repo;
     
    public List<Game> listAll() {
        return repo.findAll();
    }
     
    public Game save(Game entity) {
       
        List<Stat> stats = entity.getStats();
        for (Stat stat: stats) {
            stat.setGame(entity);
        }
        return repo.save(entity);
    }
     
    public Game get(long id) {
        return repo.findById(id).get();
    }
     
    public void delete(long id) {
        repo.deleteById(id);
    }
}
