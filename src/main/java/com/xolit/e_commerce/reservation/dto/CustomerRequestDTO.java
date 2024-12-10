package com.xolit.e_commerce.reservation.dto;

import jakarta.validation.constraints.NotBlank;

public class CustomerRequestDTO {
    @NotBlank
    private String name;
    private String email;
    private String phone;

    public @NotBlank String getName() {
        return name;
    }

    public void setName(@NotBlank String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
