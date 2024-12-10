package com.xolit.e_commerce.reservation.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="customer_id", nullable = false)
    private Integer customerId;

    @Column(name="table_id", nullable = false)
    private Integer tableId;

    @Column(name="reservation_date", nullable = false)
    private LocalDate reservationDate;

    @Column(name="start_time", nullable = false)
    private LocalTime startTime;

    @Column(name="duration_hours", nullable = false)
    private Integer durationHours;

    @Column(name="persons_count", nullable = false)
    private Integer personsCount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getTableId() {
        return tableId;
    }

    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDate reservationDate) {
        this.reservationDate = reservationDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public Integer getDurationHours() {
        return durationHours;
    }

    public void setDurationHours(Integer durationHours) {
        this.durationHours = durationHours;
    }

    public Integer getPersonsCount() {
        return personsCount;
    }

    public void setPersonsCount(Integer personsCount) {
        this.personsCount = personsCount;
    }
}
