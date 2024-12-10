package com.xolit.e_commerce.reservation.repository;

import com.xolit.e_commerce.reservation.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    @Query("SELECT r FROM Reservation r WHERE r.tableId = :tableId AND r.reservationDate = :reservationDate")
    List<Reservation> findReservationsByTableAndDate(Integer tableId, LocalDate reservationDate);

}
