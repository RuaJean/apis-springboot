package com.xolit.e_commerce.reservation.service;

import com.xolit.e_commerce.reservation.dto.ReservationRequestDTO;
import com.xolit.e_commerce.reservation.dto.ReservationResponseDTO;
import com.xolit.e_commerce.reservation.entity.Customer;
import com.xolit.e_commerce.reservation.entity.Reservation;
import com.xolit.e_commerce.reservation.entity.RestaurantTable;
import com.xolit.e_commerce.reservation.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final CustomerService customerService;
    private final RestaurantTableService tableService;

    public ReservationService(ReservationRepository reservationRepository,
                              CustomerService customerService,
                              RestaurantTableService tableService) {
        this.reservationRepository = reservationRepository;
        this.customerService = customerService;
        this.tableService = tableService;
    }

    public ReservationResponseDTO createReservation(ReservationRequestDTO request) {
        Customer customer = customerService.getById(request.getCustomerId());
        RestaurantTable table = tableService.getById(request.getTableId());

        // Validar capacidad
        if (request.getPersonsCount() > table.getCapacity()) {
            throw new IllegalArgumentException("La mesa no tiene capacidad suficiente");
        }

        // Validar disponibilidad (no colisión en rango de tiempo)
        validateAvailability(table.getId(), request.getReservationDate(), request.getStartTime(), request.getDurationHours());

        Reservation reservation = new Reservation();
        reservation.setCustomerId(customer.getId());
        reservation.setTableId(table.getId());
        reservation.setReservationDate(request.getReservationDate());
        reservation.setStartTime(request.getStartTime());
        reservation.setDurationHours(request.getDurationHours());
        reservation.setPersonsCount(request.getPersonsCount());

        reservation = reservationRepository.save(reservation);
        return toResponseDTO(reservation);
    }

    private void validateAvailability(Integer tableId, LocalDate date, LocalTime startTime, Integer durationHours) {
        var sameDayReservations = reservationRepository.findReservationsByTableAndDate(tableId, date);
        LocalTime endTime = startTime.plusHours(durationHours);

        for (Reservation r : sameDayReservations) {
            LocalTime rStart = r.getStartTime();
            LocalTime rEnd = rStart.plusHours(r.getDurationHours());

            // Chequeo de colisión: si el rango [startTime, endTime) se solapa con [rStart, rEnd)
            boolean overlaps = startTime.isBefore(rEnd) && rStart.isBefore(endTime);
            if (overlaps) {
                throw new IllegalArgumentException("La mesa ya está reservada en el rango de tiempo solicitado");
            }
        }
    }

    private ReservationResponseDTO toResponseDTO(Reservation r) {
        ReservationResponseDTO dto = new ReservationResponseDTO();
        dto.setId(r.getId());
        dto.setCustomerId(r.getCustomerId());
        dto.setTableId(r.getTableId());
        dto.setReservationDate(r.getReservationDate());
        dto.setStartTime(r.getStartTime());
        dto.setDurationHours(r.getDurationHours());
        dto.setPersonsCount(r.getPersonsCount());
        return dto;
    }
}
