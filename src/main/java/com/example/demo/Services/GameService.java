package com.example.demo.Services;

import java.util.List;

import javax.transaction.Transactional;

import com.example.demo.Models.Game;
import com.example.demo.Models.Stat;
import com.example.demo.Repositories.GameRepository;
import com.example.demo.Repositories.StatRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class GameService {
    @Autowired
    private GameRepository repo;

    @Autowired 
    private  StatRepository strepo;
     
    public List<Game> listAll() {
        return repo.findAll();
    }
     
    public Game save(Game entity) {
        Game g = repo.save(entity);
        List<Stat> stats = entity.getStats();
        for (Stat stat: stats) {
            stat.setGame(g);
            strepo.save(stat);
        }
        return g;
        //return 
    }
     
    public Game get(long id) {
        return repo.findById(id).get();
    }
     
    public void delete(long id) {
        repo.deleteById(id);
    }
}
