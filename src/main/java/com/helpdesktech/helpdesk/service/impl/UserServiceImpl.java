package com.helpdesktech.helpdesk.service.impl;

import com.helpdesktech.helpdesk.dto.user.UserRequestDTO;
import com.helpdesktech.helpdesk.dto.user.UserResponseDTO;
import com.helpdesktech.helpdesk.entity.User;
import com.helpdesktech.helpdesk.enums.User.UserRole;
import com.helpdesktech.helpdesk.enums.User.UserStatus;
import com.helpdesktech.helpdesk.exception.ResourceNotFoundException;
import com.helpdesktech.helpdesk.mapper.UserMapper;
import com.helpdesktech.helpdesk.repository.UserRepository;
import com.helpdesktech.helpdesk.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tools.jackson.databind.cfg.MapperBuilder;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private String normalizePhone(String phone) {
        if (phone.startsWith("0")) {
            return "+212" + phone.substring(1);
        }
        if (phone.startsWith("212")) {
            return "+".concat(phone);
        }
        return phone;
    }

    @Override
    public UserResponseDTO CreateUserOne(UserRequestDTO userRequestDTO) {
        if (userRepository.findByEmail(userRequestDTO.email()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }
        if (userRepository.findByPhoneNumber(userRequestDTO.phoneNumber()).isPresent()) {
            throw new RuntimeException("Phone number already exists");
        }
        if (userRepository.findByFullName(userRequestDTO.fullName()).isPresent()) {
            throw new RuntimeException("Full name already exists");
        }
        User user = userMapper.toEntity(userRequestDTO);
        user.setRole(UserRole.USER);
        user.setStatus(UserStatus.ACTIVE);
        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());

    }

    @Override
    public UserResponseDTO getUserById(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return userMapper.toDto(user);
    }

    @Override
    public UserResponseDTO UpdateUser(UUID id,UserRequestDTO userRequestDTO) {
        User userUpdate = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        if (userRequestDTO.fullName() != null ) userUpdate.setFullName(userRequestDTO.fullName());
        if (userRequestDTO.password() != null ) userUpdate.setPassword(userRequestDTO.password());
        if (userRequestDTO.phoneNumber() != null ) userUpdate.setPhoneNumber(normalizePhone(userRequestDTO.phoneNumber()));
        if (userRequestDTO.department() != null ) userUpdate.setDepartment(userRequestDTO.department());
        return userMapper.toDto(userRepository.save(userUpdate));
    }
}
