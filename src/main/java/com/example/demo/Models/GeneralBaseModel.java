package com.example.demo.Models;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@MappedSuperclass
public abstract class GeneralBaseModel extends BaseModel {

    @NotEmpty(message = "Name may not be empty")
    @Size( min = 2, max = 60)
    @Column(unique = true)
    private String name;
    
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name=name;
    }
}