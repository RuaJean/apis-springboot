package com.xolit.e_commerce.library.dto;

import jakarta.validation.constraints.NotNull;

public class LoanRequestDTO {
    @NotNull
    private Integer userId;
    @NotNull
    private Integer bookId;

    public @NotNull Integer getUserId() {
        return userId;
    }

    public void setUserId(@NotNull Integer userId) {
        this.userId = userId;
    }

    public @NotNull Integer getBookId() {
        return bookId;
    }

    public void setBookId(@NotNull Integer bookId) {
        this.bookId = bookId;
    }
}
