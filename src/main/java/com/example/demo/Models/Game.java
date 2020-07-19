
package com.example.demo.Models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;


@Entity
public class Game extends GeneralBaseModel {
    
    private List<Stat> stats = new ArrayList<>();

    public void setStats(List<Stat> stats){
        this.stats= stats;
    }

    @OneToMany(
        mappedBy = "game",
        fetch = FetchType.EAGER
    )
    public List<Stat> getStats(){
        return stats;
    }
 
}