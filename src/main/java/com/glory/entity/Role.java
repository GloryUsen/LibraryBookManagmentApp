package com.glory.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
// import lombok.AllArgsConstructor;
// import lombok.Getter;
// import lombok.NoArgsConstructor;
// import lombok.Setter;

// @Getter
// @Setter
@Entity
@Table(name = "roles")
// @NoArgsConstructor
// @AllArgsConstructor

public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)



    private Long id;

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }



    private String name;

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
        
    }


    @JsonIgnore
    @OneToMany(mappedBy = "role")
    private List<User> users;

    public List<User> getUsers(){
        return users;
    }

    public void setUsers(List<User> users){
        this.users = users;
    }


}
