package com.example.eserciziodto.repository;

import com.example.eserciziodto.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT b.status FROM Book b WHERE b.id = :id")
    Optional<String> findStatusById(@Param("id") Long id);
}
