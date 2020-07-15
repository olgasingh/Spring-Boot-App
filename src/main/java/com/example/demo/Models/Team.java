
package com.example.demo.Models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Team extends GeneralBaseModel {
    
    private List<Product> products = new ArrayList<>();

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @OneToMany(
        mappedBy = "category"
    )
    public List<Product> getProducts(){
        return this.products;
    }
 
}