package com.helpdesktech.helpdesk.dto.user;

import com.helpdesktech.helpdesk.enums.User.UserRole;
import com.helpdesktech.helpdesk.enums.User.UserStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserRequestDTO(
        @NotBlank(message = "Full name is required")
        @Size(max = 100)
        String fullName,

        @Email(message = "Email should be valid")
        @NotBlank(message = "Email is required")
        @Size(max = 150)
        String email,

        @NotBlank(message = "Password is required")
        @Size(min = 8, message = "Password must be at least 8 characters")
        String password,


        @Pattern(
                regexp = "^(?:\\+212|212|0)([67])\\d{8}$",
                message = "Phone number must be a valid Moroccan mobile number"
        )
        @NotBlank(message = "Phone number is required")
        String phoneNumber,

        @NotBlank(message = "Department is required")
        String department,

        UserRole role,

        UserStatus status
) {
}
