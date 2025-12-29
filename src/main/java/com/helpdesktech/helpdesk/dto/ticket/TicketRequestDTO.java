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

        @NotNull(message = "Priority is required")
        TicketPriority priority,

        @NotBlank(message = "Category is required")
        @Size(max = 100)
        String category,

        UUID deviceId
) {
}

