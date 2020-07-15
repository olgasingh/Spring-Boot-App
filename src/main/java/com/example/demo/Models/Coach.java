package com.example.demo.Models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

@Entity
public class Coach extends BaseModel {
    
    @NotEmpty(message = "First name may not be empty")
    private String firstname;

    @NotEmpty(message = "Last name may not be empty")
    private String lastname;
    
 
    private Team team;
    
    public String getFirstname(){
        return firstname;
    }
    public String getLastname(){
        return lastname;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="teamid")
    public Team getTeam(){
        return team;
    }

    
    public void setFirstname(String firstname){
        this.firstname=firstname;
    }
    public void setLastname(String lastname){
        this.lastname=lastname;
    }
    public void setTeam(Team team){
        this.team=team;
    }

    private List<Stat> stats;
    
    @OneToMany(
        mappedBy = "coach"
    )
    public List<Stat> getStats(){
        return stats;
    }

    public void setStats(List<Stat> stats){
        this.stats = stats;

    }

}