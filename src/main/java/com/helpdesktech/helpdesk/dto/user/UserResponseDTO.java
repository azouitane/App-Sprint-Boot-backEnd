package com.helpdesktech.helpdesk.dto.user;

import com.helpdesktech.helpdesk.enums.User.UserRole;
import com.helpdesktech.helpdesk.enums.User.UserStatus;

import java.util.UUID;

public record UserResponseDTO(
        UUID id,
        String fullName,
        String email,
        String phoneNumber,
        String department,
        UserRole role,
        UserStatus status
) {
}
