package com.helpdesktech.helpdesk.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UpdateUserDTO(
        @Size(max = 100)
        String fullName,

        @Size(min = 6)
        String password,

        @Pattern(
                regexp = "^[0-9]{10,15}$",
                message = "Phone number must contain only digits (10 to 15)"
        )
        String phoneNumber,

        String department
) {
}
