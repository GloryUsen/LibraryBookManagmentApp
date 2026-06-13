package com.glory.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.glory.entity.Book;


@Repository

public interface BookRepository extends JpaRepository<Book, Long>{

            @Query("SELECT b FROM Book b JOIN FETCH b.category")
        Page<Book> findAllWithCategory(Pageable pageable);

}
