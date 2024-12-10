package com.xolit.e_commerce.reservation.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

public class ReservationRequestDTO {
    @NotNull
    private Integer customerId;
    @NotNull
    private Integer tableId;
    @NotNull
    private LocalDate reservationDate;
    @NotNull
    private LocalTime startTime;
    @NotNull
    private Integer durationHours;
    @NotNull
    private Integer personsCount;

    public @NotNull Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(@NotNull Integer customerId) {
        this.customerId = customerId;
    }

    public @NotNull Integer getTableId() {
        return tableId;
    }

    public void setTableId(@NotNull Integer tableId) {
        this.tableId = tableId;
    }

    public @NotNull LocalDate getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(@NotNull LocalDate reservationDate) {
        this.reservationDate = reservationDate;
    }

    public @NotNull LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(@NotNull LocalTime startTime) {
        this.startTime = startTime;
    }

    public @NotNull Integer getDurationHours() {
        return durationHours;
    }

    public void setDurationHours(@NotNull Integer durationHours) {
        this.durationHours = durationHours;
    }

    public @NotNull Integer getPersonsCount() {
        return personsCount;
    }

    public void setPersonsCount(@NotNull Integer personsCount) {
        this.personsCount = personsCount;
    }
}
