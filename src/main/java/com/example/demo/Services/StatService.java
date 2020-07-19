package com.example.demo.Services;

import java.util.List;

import javax.transaction.Transactional;

import com.example.demo.Repositories.StatRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class StatService {
    @Autowired
    private StatRepository repo;

    public List<Object[]> getTop5(String stField){
        return repo.getTopFive(stField);
    }

    public List<Object[]> getButtomFive(String stField){
        return repo.getButtomFive(stField);
    }
     
   
}
