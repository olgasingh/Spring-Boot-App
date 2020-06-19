package com.example.demo.Models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Role extends GeneralBaseModel {

    
private List<User> users;

public void setUsers(List<User> users){
    this.users = users;
    
}
@JsonIgnore
@ManyToMany(mappedBy = "roles")
public List<User> getUsers(){
    return this.users;
}

/*
@Override
public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
    return result;
}

@Override
public boolean equals(Object obj) {
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    Role other = (Role) obj;
    if (getId() == null) {
        if (other.getId() != null)
            return false;
    } else if (!getId().equals(other.getId()))
        return false;
    return true;
}  
  */
}