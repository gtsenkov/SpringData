package com.softuni.springintoexercise.controllers;

import com.softuni.springintoexercise.entities.Book;
import com.softuni.springintoexercise.services.AuthorService;
import com.softuni.springintoexercise.services.BookService;
import com.softuni.springintoexercise.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.io.BufferedReader;

@Controller
public class AppController implements CommandLineRunner {

    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;
    private final BufferedReader bufferedReader;

    @Autowired
    public AppController(CategoryService categoryService, AuthorService authorService, BookService bookService, BufferedReader bufferedReader) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
        this.bufferedReader = bufferedReader;
    }

    @Override
    public void run(String... args) throws Exception {
        //seed data
        this.categoryService.seedCategories();
        this.authorService.seedAuthors();
        this.bookService.seedBooks();

        //Ex 1
        //List<Book> allBooksAfter2000 = this.bookService.getAllBooksAfter2000();

        //Ex 3
//        this.authorService.findAllAuthorsByCountOfBooks()
//                .forEach(a -> {
//                    System.out.printf("%s %s %d%n", a.getFirstName(), a.getLastName(),
//                            a.getBooks().size());
//                });

        //Unit 14

        //Ex 1.	Books Titles by Age Restriction
//        System.out.println("Enter age restriction:");
//        this.bookService
//                .getAllBooksByAgeRestriction(this.bufferedReader.readLine())
//                .stream()
//                .map(Book::getTitle)
//                .forEach(System.out::println);

        //Ex 2.	Golden Books
//        this.bookService
//                .getAllBooksByEditionTypeAndCopies("gold", 5000)
//                .stream()
//                .map(Book::getTitle)
//                .forEach(System.out::println);


        //Ex 4.	Not Released Books
//        System.out.println("Enter year:");
//        this.bookService
//                .getAllBooksByReleaseDateNotInYear(
//                        Integer.parseInt(this.bufferedReader.readLine()))
//                .forEach(b -> {
//                    System.out.printf("%s %n", b.getTitle());
//                });

        // Ex5.	Books Released Before Date
//        System.out.println("Enter date:");
//        this.bookService
//                .getAllByReleaseDate(this.bufferedReader.readLine())
//                .forEach(book -> {
//                    System.out.printf("%s %s %.2f%n",
//                            book.getTitle(), book.getEditionType(), book.getPrice());
//                });

        // Ex6.	Authors Search
//        System.out.println("Enter endsWith String:");
//        this.authorService
//                .getAuthorsByFirstNameEndsWith(this.bufferedReader.readLine())
//                .forEach(author -> {
//                    System.out.printf("%s %s%n", author.getFirstName(), author.getLastName());
//                });


        // Ex7.	Books Search
//        System.out.println("Enter containing string:");
//        this.bookService.getAllBooksByTitleContainsString(this.bufferedReader.readLine())
//                .stream()
//                .map(Book::getTitle)
//                .forEach(System.out::println);

        // Ex10.    Total Book Copies
//        System.out.println("Enter full name:");
//        System.out.println(this.bookService
//        .getTotalCopiesByAuthor(this.bufferedReader.readLine()));

        // Ex12.	Increase Book Copies
        System.out.println("Enter date and copies: ");
        String date = this.bufferedReader.readLine();
        int copies = Integer.parseInt(this.bufferedReader.readLine());

        int totalCopies = this.bookService
                .updateBooksCopiesAfterDate(date, copies) * copies;

        System.out.println(totalCopies);
    }
}
