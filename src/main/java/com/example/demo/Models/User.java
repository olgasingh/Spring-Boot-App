package com.example.demo.Models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class User extends BaseModel {
    private String firstname;
    private String lastname;
    
    @DateTimeFormat (pattern="MM/dd/YYYY")
    private Date dob;
    private String password;
    private String email;

    private List<Role> roles = new ArrayList<>();
 
    
    public String getFirstname(){
        return firstname;
    }
    public String getLastname(){
        return lastname;
    }
    public Date getDob() {
        return dob;
    }
    public String getPassword(){
        return password;
    }
    public String getEmail(){
        return email;
    }

    
    public void setFirstname(String firstname){
        this.firstname=firstname;
    }
    public void setLastname(String lastname){
        this.lastname=lastname;
    }
    public void setDob(Date dob){
        this.dob=dob;
    }
    public void setPassword(String password){
        this.password=password;
    }
    public void setEmail(String email){
        this.email=email;
    
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "roleuser", joinColumns = {@JoinColumn(name="userid")},
    inverseJoinColumns = {@JoinColumn(name="roleid")})
    public List<Role> getRoles(){
        return this.roles;
    }

    public void setRoles(List<Role> roles){
        this.roles = roles;
    }

}