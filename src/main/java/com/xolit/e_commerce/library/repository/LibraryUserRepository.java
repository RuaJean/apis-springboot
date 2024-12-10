package com.xolit.e_commerce.library.repository;

import com.xolit.e_commerce.library.entity.LibraryUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryUserRepository extends JpaRepository<LibraryUser, Integer> {
}
