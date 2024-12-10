package com.xolit.e_commerce.library.repository;

import com.xolit.e_commerce.library.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan, Integer> {
}
