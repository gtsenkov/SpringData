package com.softuni.springintoexercise.repositories;

import com.softuni.springintoexercise.entities.AgeRestriction;
import com.softuni.springintoexercise.entities.Book;
import com.softuni.springintoexercise.entities.Category;
import com.softuni.springintoexercise.entities.EditionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByReleasedDateAfter(LocalDate localDate);

    //Unit 14 Advanced Querying

    //Ex 1
    List<Book> findAllByAgeRestriction(AgeRestriction ageRestriction);

    //Ex 2
    List<Book> findAllByEditionTypeAndCopiesLessThan(EditionType editionType, int copies);

    //Ex 3
   // List<Book> findAllByPriceLessThanAndPriceGreaterThan(BigDecimal lowerPrice, BigDecimal greaterPrice);

    //Ex 4
    List<Book> findAllByReleasedDateBeforeOrReleasedDateAfter(LocalDate before, LocalDate after);


    //Ex 5
    List<Book> findAllByReleasedDateBefore(LocalDate releaseDate);

    //Ex 7
    List<Book> findAllByTitleContains(String str);

    //Ex 10
    @Query("SELECT sum(b.copies) FROM Book AS b WHERE concat(b.author.firstName, ' ',b.author.lastName) = :name")
    int findAllCopiesByAuthor(@Param("name") String fullName);



    //Ex 12
    @Modifying
    @Query("UPDATE Book AS b SET b.copies = b.copies + :copies WHERE b.releasedDate > :date")
    int updateAllBookAfterGivenDate(@Param("date") LocalDate date,@Param("copies") int copies);



}
