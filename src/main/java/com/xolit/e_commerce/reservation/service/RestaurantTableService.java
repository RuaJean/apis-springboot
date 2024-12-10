package com.xolit.e_commerce.reservation.service;

import com.xolit.e_commerce.reservation.dto.RestaurantTableRequestDTO;
import com.xolit.e_commerce.reservation.dto.RestaurantTableResponseDTO;
import com.xolit.e_commerce.reservation.entity.RestaurantTable;
import com.xolit.e_commerce.reservation.repository.RestaurantTableRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantTableService {
    private final RestaurantTableRepository tableRepository;
    public RestaurantTableService(RestaurantTableRepository tableRepository) {
        this.tableRepository = tableRepository;
    }

    public RestaurantTableResponseDTO createTable(RestaurantTableRequestDTO request) {
        RestaurantTable t = new RestaurantTable();
        t.setTableNumber(request.getTableNumber());
        t.setCapacity(request.getCapacity());
        t = tableRepository.save(t);
        return toResponseDTO(t);
    }

    public RestaurantTable getById(Integer id) {
        return tableRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("La mesa no existe"));
    }

    public List<RestaurantTableResponseDTO> listAll() {
        return tableRepository.findAll().stream().map(this::toResponseDTO).collect(Collectors.toList());
    }

    private RestaurantTableResponseDTO toResponseDTO(RestaurantTable t) {
        RestaurantTableResponseDTO dto = new RestaurantTableResponseDTO();
        dto.setId(t.getId());
        dto.setTableNumber(t.getTableNumber());
        dto.setCapacity(t.getCapacity());
        return dto;
    }
}
