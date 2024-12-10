package com.xolit.e_commerce.library.service;

import com.xolit.e_commerce.library.dto.BookRequestDTO;
import com.xolit.e_commerce.library.dto.BookResponseDTO;
import com.xolit.e_commerce.library.entity.Book;
import com.xolit.e_commerce.library.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    private final BookRepository bookRepository;
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookResponseDTO createBook(BookRequestDTO request) {
        Book book = new Book();
        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());
        book.setPublicationYear(request.getPublicationYear());
        book.setGenre(request.getGenre());
        book.setAvailable(true);
        book = bookRepository.save(book);
        return toResponseDTO(book);
    }

    public List<BookResponseDTO> listAvailableBooks(String title, String genre) {
        List<Book> books;
        if (title != null && !title.isEmpty()) {
            books = bookRepository.findByTitleContainingIgnoreCaseAndAvailableTrue(title);
        } else if (genre != null && !genre.isEmpty()) {
            books = bookRepository.findByGenreContainingIgnoreCaseAndAvailableTrue(genre);
        } else {
            books = bookRepository.findByAvailableTrue();
        }
        return books.stream().map(this::toResponseDTO).collect(Collectors.toList());
    }

    public BookResponseDTO toResponseDTO(Book book) {
        BookResponseDTO dto = new BookResponseDTO();
        dto.setId(book.getId());
        dto.setTitle(book.getTitle());
        dto.setAuthor(book.getAuthor());
        dto.setPublicationYear(book.getPublicationYear());
        dto.setGenre(book.getGenre());
        dto.setAvailable(book.getAvailable());
        return dto;
    }
}
