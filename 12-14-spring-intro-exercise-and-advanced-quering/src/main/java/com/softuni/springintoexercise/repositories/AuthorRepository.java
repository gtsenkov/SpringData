package com.softuni.springintoexercise.repositories;

import com.softuni.springintoexercise.entities.Author;
import com.softuni.springintoexercise.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query("SELECT a FROM Author AS a ORDER BY a.books.size DESC")
    List<Author> findAuthorByCountOfBook();

    //Unit 4
    //Ex 6
    List<Author> findAllByFirstNameEndingWith(String endsWith);
}
