package com.helpdesktech.helpdesk.service.impl;

import com.helpdesktech.helpdesk.dto.user.UpdateUserDTO;
import com.helpdesktech.helpdesk.dto.user.UserRequestDTO;
import com.helpdesktech.helpdesk.dto.user.UserResponseDTO;
import com.helpdesktech.helpdesk.entity.User;
import com.helpdesktech.helpdesk.enums.User.UserRole;
import com.helpdesktech.helpdesk.enums.User.UserStatus;
import com.helpdesktech.helpdesk.exception.DuplicateResourceException;
import com.helpdesktech.helpdesk.exception.ResourceNotFoundException;
import com.helpdesktech.helpdesk.mapper.UserMapper;
import com.helpdesktech.helpdesk.repository.UserRepository;
import com.helpdesktech.helpdesk.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private String normalizePhone(String phone) {
        if (phone.startsWith("0")) return "+212" + phone.substring(1);
        if (phone.startsWith("212")) return "+" + phone;
        return phone;
    }

    @Override
    public UserResponseDTO createUserOne(UserRequestDTO userRequestDTO) {
        if (userRepository.findByEmail(userRequestDTO.email()).isPresent())
            throw new DuplicateResourceException("Email already exists");
        if (userRepository.findByPhoneNumber(userRequestDTO.phoneNumber()).isPresent())
            throw new DuplicateResourceException("Phone number already exists");
        if (userRepository.findByFullName(userRequestDTO.fullName()).isPresent())
            throw new DuplicateResourceException("Full name already exists");

        User user = userMapper.toEntity(userRequestDTO);
        user.setPhoneNumber(normalizePhone(userRequestDTO.phoneNumber()));
        user.setRole(UserRole.USER);
        user.setStatus(UserStatus.ACTIVE);

        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());

    }

    @Override
    public UserResponseDTO getUserById(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found" + id));
        return userMapper.toDto(user);
    }

    @Override
    public UserResponseDTO updateUser(UUID id, UpdateUserDTO updateUserDTO) {
        User userUpdate = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + id));

        if (updateUserDTO.fullName() != null) userUpdate.setFullName(updateUserDTO.fullName());
        if (updateUserDTO.password() != null) userUpdate.setPassword(updateUserDTO.password());
        if (updateUserDTO.phoneNumber() != null) userUpdate.setPhoneNumber(normalizePhone(updateUserDTO.phoneNumber()));
        if (updateUserDTO.department() != null) userUpdate.setDepartment(updateUserDTO.department());

        return userMapper.toDto(userRepository.save(userUpdate));
    }

}
