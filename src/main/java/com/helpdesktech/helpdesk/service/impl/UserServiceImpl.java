package com.helpdesktech.helpdesk.service.impl;

import com.helpdesktech.helpdesk.dto.glopal.GlopalResponse;
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
import com.helpdesktech.helpdesk.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    private String normalizePhone(String phone) {
        if (phone.startsWith("0")) return "+212" + phone.substring(1);
        if (phone.startsWith("212")) return "+" + phone;
        return phone;
    }

    @Override
    public GlopalResponse createUserOne(UserRequestDTO userRequestDTO) {

        String normalizedPhone = normalizePhone(userRequestDTO.phoneNumber());

        if (userRepository.findByEmail(userRequestDTO.email()).isPresent())
            throw new DuplicateResourceException("Email already exists");

        if (userRepository.findByPhoneNumber(normalizedPhone).isPresent())
            throw new DuplicateResourceException("Phone number already exists");

        if (userRepository.findByFullName(userRequestDTO.fullName()).isPresent())
            throw new DuplicateResourceException("Full name already exists");

        User user = userMapper.toEntity(userRequestDTO);

        user.setPassword(passwordEncoder.encode(userRequestDTO.password()));
        user.setPhoneNumber(normalizedPhone);
        user.setRole(UserRole.USER);
        user.setStatus(UserStatus.ACTIVE);

        userMapper.toDto(userRepository.save(user));

        return new GlopalResponse("User created successfully");
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
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + id));
        return userMapper.toDto(user);
    }

    @Override
    public GlopalResponse updateUser(UUID id, UpdateUserDTO updateUserDTO) {
        User userUpdate = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + id));

        // Full name
        if (updateUserDTO.fullName() != null) {
            // Check duplicate full name for other users
            userRepository.findByFullName(updateUserDTO.fullName())
                    .filter(u -> !u.getId().equals(id))
                    .ifPresent(u -> { throw new DuplicateResourceException("Full name already exists"); });
            userUpdate.setFullName(updateUserDTO.fullName());
        }

        // Password
        if (updateUserDTO.password() != null) {
            userUpdate.setPassword(passwordEncoder.encode(updateUserDTO.password()));
        }

        // Phone number
        if (updateUserDTO.phoneNumber() != null) {
            String normalizedPhone = normalizePhone(updateUserDTO.phoneNumber());

            // Check duplicate phone for other users
            userRepository.findByPhoneNumber(normalizedPhone)
                    .filter(u -> !u.getId().equals(id))
                    .ifPresent(u -> { throw new DuplicateResourceException("Phone number already exists"); });

            userUpdate.setPhoneNumber(normalizedPhone);
        }
        // Department
        if (updateUserDTO.department() != null) {
            userUpdate.setDepartment(updateUserDTO.department());
        }

        userMapper.toDto(userRepository.save(userUpdate));

        return new GlopalResponse("Update user successfully");
    }


}
