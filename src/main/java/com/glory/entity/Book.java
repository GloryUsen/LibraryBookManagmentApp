package com.glory.entity;

import com.glory.enums.BookStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name= "books")
@AllArgsConstructor
@NoArgsConstructor


public class Book {

     @Id
     @GeneratedValue(strategy= GenerationType.IDENTITY)

     private Long id;
     private String title;
     private String author;
     private String isbn;
     private boolean isAvailable;

    @Enumerated(EnumType.STRING)
     private BookStatus status;

     @ManyToOne
     @JoinColumn(name = "category_id")
     private Category category;

}
