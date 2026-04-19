package com.glory.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;




@Entity
@Table(name = "loans")
@NoArgsConstructor
@AllArgsConstructor



public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    
    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    @CreationTimestamp
    private LocalDate borrowDate;

    public LocalDate getBorrowDate(){
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate){
        this.borrowDate = borrowDate;
    }



    private LocalDate returnedDate;

    public LocalDate getReturnedDate(){
        return returnedDate;
    }

    public void setReturnedDate(LocalDate returnedDate){
        this.returnedDate = returnedDate;
    }



    private boolean returned = false;

    public boolean isReturned(){
        return returned;
    }

    public void setReturned(boolean returned){
        this.returned = returned;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public User getUser(){
        return user;
    }

    public void setUser(User user){
        this.user = user;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    public Book getBook(){
        return book;
    }

    public void setBook(Book book){
        this.book = book;
    }
    

}
