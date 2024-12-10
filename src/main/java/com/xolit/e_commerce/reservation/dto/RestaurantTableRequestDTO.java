package com.xolit.e_commerce.reservation.dto;

import jakarta.validation.constraints.NotNull;

public class RestaurantTableRequestDTO {
    @NotNull
    private Integer tableNumber;
    @NotNull
    private Integer capacity;

    public @NotNull Integer getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(@NotNull Integer tableNumber) {
        this.tableNumber = tableNumber;
    }

    public @NotNull Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(@NotNull Integer capacity) {
        this.capacity = capacity;
    }
}
