package com.helpdesktech.helpdesk.service.interfaces;

import com.helpdesktech.helpdesk.dto.glopal.GlopalResponse;
import com.helpdesktech.helpdesk.dto.user.UpdateUserDTO;
import com.helpdesktech.helpdesk.dto.user.UserRequestDTO;
import com.helpdesktech.helpdesk.dto.user.UserResponseDTO;

import java.util.List;
import java.util.UUID;

public interface UserService {
    // Create User One
     GlopalResponse createUserOne(UserRequestDTO userRequestDTO);

    // Get All Users
    List<UserResponseDTO> getAllUsers();

    // Get User By Id
    UserResponseDTO getUserById(UUID id);

    // Update User One
    GlopalResponse updateUser(UUID id, UpdateUserDTO  updateUserDTO);
}

