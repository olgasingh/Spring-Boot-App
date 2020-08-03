package com.example.demo.Models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

 
@Entity
public class Product extends GeneralBaseModel implements Serializable {
    
    /**
     *
     */
    private static final long serialVersionUID = -2179975668024949664L;

    @NotEmpty(message = "Brand may not be empty")
    private String brand;

    private String madein;
    private float price;
 
    private Category category;
    
    public String getBrand(){
        return brand;
    }
    public String getMadein(){
        return madein;
    }
    public float getPrice(){
        return price;
    }

    
    public void setBrand(String brand){
        this.brand=brand;
    }
    public void setMadein(String madein){
        this.madein=madein;
    }
    public void setPrice(float price){
        this.price=price;
    }

    public void setCategory(Category category){
        this.category = category;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="categoryid")
    public Category getCategory() {
        return category;
    }



     
    // other getters and setters are hidden for brevity
}