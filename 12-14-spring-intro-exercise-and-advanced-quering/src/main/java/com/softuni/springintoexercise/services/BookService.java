package com.softuni.springintoexercise.services;

import com.softuni.springintoexercise.entities.Book;
import org.w3c.dom.stylesheets.LinkStyle;

import java.io.IOException;
import java.util.List;

public interface BookService {
    void seedBooks() throws IOException;

    List<Book> getAllBooksAfter2000();

    //Unit 14 Advanced Querying

    //Ex 1
    List<Book> getAllBooksByAgeRestriction(String ageRestriction);

    //Ex 2
    List<Book> getAllBooksByEditionTypeAndCopies(String editionType, int copies);

    //Ex 4
    List<Book> getAllBooksByReleaseDateNotInYear(int year);

    //Ex 5
    List<Book> getAllByReleaseDate(String date);

    //Ex 7
    List<Book> getAllBooksByTitleContainsString(String str);

    //Ex 10
    int getTotalCopiesByAuthor(String fullName);

    //Ex 12
    int updateBooksCopiesAfterDate(String date, int copies);
}
