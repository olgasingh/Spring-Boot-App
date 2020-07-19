
package com.example.demo.Models;

import java.util.List;

import javax.persistence.Entity;

import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

@Entity
public class Player extends BaseModel {
    
   private List<Stat> stats;

    public void setStats(List<Stat> stats) {
        this.stats = stats;
    }

    @OneToMany(mappedBy = "player")
    public List<Stat> getStats(){
        return this.stats;
    }

    @NotEmpty(message = "First name may not be empty")
    private String firstname;

    @NotEmpty(message = "Last name may not be empty")
    private String lastname;

    private int age;

    private String college;

    private String hoemtown;

    private int height;

    private int weight;

    private String position;
    
 
    public String getFirstname(){
        return firstname;
    }
    public String getLastname(){
        return lastname;
    }
    
    public void setFirstname(String firstname){
        this.firstname=firstname;
    }
    public void setLastname(String lastname){
        this.lastname=lastname;
    }

    public int  getAge(){
        return age;
    }

    public void setAge(int age){
        this.age= age;
    }

    public String getCollege(){
        return this.college;
    }

    public void setCollege(String college){
        this.college = college;
    }

    public String getHometown(){
        return this.hoemtown;
    }

    public void setHometown(String hometown){
        this.hoemtown = hometown;
    }

    public int getHeight(){
        return this.height;
    }
    public void setHeight(int height){
        this.height = height;
    }

    public int getWeight(){
        return this.weight;
    }

    public void setWeight(int  weight){
        this.weight = weight;
    }
    
    
    public String getPosition(){
        return this.position;
    }

    public void setPosition(String position){
        this.position = position;
    }

 
}