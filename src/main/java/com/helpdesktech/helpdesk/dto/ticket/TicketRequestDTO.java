package com.helpdesktech.helpdesk.dto.ticket;

import com.helpdesktech.helpdesk.enums.Ticket.TicketPriority;
import com.helpdesktech.helpdesk.enums.Ticket.TicketStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record TicketRequestDTO(
        @NotBlank(message = "Title is required")
        @Size(max = 150)
        String title,

        @NotBlank(message = "Description is required")
        @Size(max = 1000)
        String description,

        TicketPriority priority,

        @Size(max = 100)
        String category,

        @NotNull(message = "Requester ID is required")
        UUID requesterId,

        UUID deviceId  // اختياري
) {
}
