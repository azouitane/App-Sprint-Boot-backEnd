package com.helpdesktech.helpdesk.dto.Error;

import java.time.LocalDateTime;

public record ErrorResponseDTO(
        LocalDateTime timestamp,
        int status,
        String error,
        String path
) {
}
