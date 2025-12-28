package com.helpdesktech.helpdesk.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UpdateUserDTO(
        @NotBlank(message = "Full name is required")
        @Size(max = 100)
        String fullName,

        @NotBlank(message = "Password is required")
        @Size(min = 6)
        String password,

        @Pattern(
                regexp = "^[0-9]{10,15}$",
                message = "Phone number must contain only digits (10 to 15)"
        )
        @NotBlank(message = "Phone Number is required")
        String phoneNumber,

        @NotBlank(message = "Department is required")
        String department
) {
}
