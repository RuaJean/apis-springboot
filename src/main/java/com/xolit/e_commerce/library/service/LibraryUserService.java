package com.xolit.e_commerce.library.service;

import com.xolit.e_commerce.library.dto.UserRequestDTO;
import com.xolit.e_commerce.library.dto.UserResponseDTO;
import com.xolit.e_commerce.library.entity.LibraryUser;
import com.xolit.e_commerce.library.repository.LibraryUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LibraryUserService {
    private final LibraryUserRepository userRepository;

    public LibraryUserService(LibraryUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponseDTO createUser(UserRequestDTO request) {
        LibraryUser user = new LibraryUser();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user = userRepository.save(user);
        return toResponseDTO(user);
    }

    public List<UserResponseDTO> listUsers() {
        return userRepository.findAll().stream().map(this::toResponseDTO).collect(Collectors.toList());
    }

    public LibraryUser getUserById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("El usuario no existe"));
    }

    private UserResponseDTO toResponseDTO(LibraryUser user) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setPhone(user.getPhone());
        return dto;
    }
}
