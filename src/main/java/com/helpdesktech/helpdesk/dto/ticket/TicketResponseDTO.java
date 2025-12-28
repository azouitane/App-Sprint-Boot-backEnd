package com.helpdesktech.helpdesk.dto.ticket;

import com.helpdesktech.helpdesk.enums.Ticket.TicketPriority;
import com.helpdesktech.helpdesk.enums.Ticket.TicketStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record TicketResponseDTO(
        UUID id,
        String title,
        String description,
        TicketStatus status,
        TicketPriority priority,
        String category,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        UUID requesterId,
        UUID deviceId  // null if no device
) {
}
