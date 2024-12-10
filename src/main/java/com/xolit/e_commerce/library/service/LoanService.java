package com.xolit.e_commerce.library.service;

import com.xolit.e_commerce.library.dto.LoanRequestDTO;
import com.xolit.e_commerce.library.dto.LoanResponseDTO;
import com.xolit.e_commerce.library.entity.Book;
import com.xolit.e_commerce.library.entity.Loan;
import com.xolit.e_commerce.library.entity.LibraryUser;
import com.xolit.e_commerce.library.repository.BookRepository;
import com.xolit.e_commerce.library.repository.LoanRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LoanService {
    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;
    private final LibraryUserService userService;

    public LoanService(LoanRepository loanRepository, BookRepository bookRepository, LibraryUserService userService) {
        this.loanRepository = loanRepository;
        this.bookRepository = bookRepository;
        this.userService = userService;
    }

    public LoanResponseDTO createLoan(LoanRequestDTO request) {
        LibraryUser user = userService.getUserById(request.getUserId());
        Book book = bookRepository.findById(request.getBookId())
                .orElseThrow(() -> new IllegalArgumentException("El libro no existe"));

        if(!book.getAvailable()) {
            throw new IllegalArgumentException("El libro ya está prestado");
        }

        book.setAvailable(false);
        bookRepository.save(book);

        Loan loan = new Loan();
        loan.setUserId(user.getId());
        loan.setBookId(book.getId());
        loan.setLoanDate(LocalDateTime.now());
        loan = loanRepository.save(loan);

        return toResponseDTO(loan);
    }

    public LoanResponseDTO returnBook(Integer loanId) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new IllegalArgumentException("Préstamo no encontrado"));
        if(loan.getReturnDate() != null) {
            throw new IllegalArgumentException("El libro ya fue devuelto");
        }

        Book book = bookRepository.findById(loan.getBookId())
                .orElseThrow(() -> new IllegalArgumentException("El libro no existe"));

        // Marcar el libro como disponible nuevamente
        book.setAvailable(true);
        bookRepository.save(book);

        loan.setReturnDate(LocalDateTime.now());
        loan = loanRepository.save(loan);
        return toResponseDTO(loan);
    }

    private LoanResponseDTO toResponseDTO(Loan loan) {
        LoanResponseDTO dto = new LoanResponseDTO();
        dto.setId(loan.getId());
        dto.setBookId(loan.getBookId());
        dto.setUserId(loan.getUserId());
        dto.setLoanDate(loan.getLoanDate());
        dto.setReturnDate(loan.getReturnDate());
        return dto;
    }
}
