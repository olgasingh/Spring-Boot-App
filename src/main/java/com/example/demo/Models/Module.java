package com.example.demo.Models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.JoinColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Module extends GeneralBaseModel{
    private List<Role> roles = new ArrayList<>(); 

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "rolemodule", joinColumns = {@JoinColumn(name="moduleid")},
    inverseJoinColumns = {@JoinColumn(name="roleid")})
    public List<Role> getRoles(){
        return this.roles;
    }

    public void setRoles(List<Role> roles){
        this.roles = roles;
    }
}