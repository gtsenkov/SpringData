package com.softuni.springintoexercise.services;

import com.softuni.springintoexercise.entities.Author;

import java.io.IOException;
import java.util.List;

public interface AuthorService {
    void seedAuthors() throws IOException;

    int getAllAuthorsCount();

    Author findAuthorById(Long id);

    List<Author> findAllAuthorsByCountOfBooks();

    //Unit 4
    //Ex 6
    List<Author> getAuthorsByFirstNameEndsWith(String endsWith);
}
