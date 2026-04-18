package com.glory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.glory.entity.Book;


@Repository

public interface BookRepository extends JpaRepository<Book, Long>{

}
