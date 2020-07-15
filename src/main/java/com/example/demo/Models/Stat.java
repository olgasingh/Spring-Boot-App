
package com.example.demo.Models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Stat extends BaseModel {
    
   private Game game;

    public void setGame(Game game) {
        this.game = game;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="gameid", referencedColumnName = "id")
    public Game getGame(){
        return this.game;
    }

    private Player player;
    public void setPlayer(Player player) {
        this.player = player;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="playerid")
    public Player getPlayer(){
        return this.player;
    }
    
    private int point;

    private int rebound;

    private Coach coach = new Coach();


    public int  getPoint(){
        return point;
    }

    public void setPoint(int point){
        this.point= point;
    }

    public int  getRebound(){
        return rebound;
    }

    public void setRebound(int rebound){
        this.rebound= rebound;
    }
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="coachid",referencedColumnName = "id")
    public Coach getCoach(){
        return this.coach;
    }

    public void setCoach(Coach coach){
        this.coach = coach;
    }
}