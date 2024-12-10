package com.xolit.e_commerce.library.repository;

import com.xolit.e_commerce.library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findByAvailableTrue();
    List<Book> findByTitleContainingIgnoreCaseAndAvailableTrue(String title);
    List<Book> findByGenreContainingIgnoreCaseAndAvailableTrue(String genre);
}
