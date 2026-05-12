package com.glory.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
 import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
// import lombok.AllArgsConstructor;

// import lombok.NoArgsConstructor;


@Entity
@Table(name = "users", uniqueConstraints = {
    @UniqueConstraint(columnNames = "email")
})
// @AllArgsConstructor
// @NoArgsConstructor


public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    public Long getId() {
    return id;
}

public void setId(Long id) {
     this.id = id;
}


    private String name;

    public String getName() {
    return name;
}

public void setName(String name) {
    this.name = name;
}

    @Column(nullable = false, unique = true)
    private String email;



    public String getEmail() {
    return email;
}

public void setEmail(String email) {
    this.email = email;
}


    private String password;

       public String getPassword() {
    return password;
}

public void setPassword(String password) {
    this.password = password;
}

    @ManyToOne
    @JoinColumn(name = "role_id")
     private Role role;

    public Role getRole() {
    return role;
}

    public void setRole(Role role) {
    this.role = role;
}


     @JsonIgnore
     @OneToMany(mappedBy = "user")
      private List<Loan> loans;

    public List<Loan>  getLoan() {
    return loans;
}

public void setLoan(List<Loan> loans) {
    this.loans = loans;
}




}
