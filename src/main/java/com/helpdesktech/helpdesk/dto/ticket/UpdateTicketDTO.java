package com.helpdesktech.helpdesk.dto.ticket;

import com.helpdesktech.helpdesk.enums.Ticket.TicketPriority;
import com.helpdesktech.helpdesk.enums.Ticket.TicketStatus;

public record UpdateTicketDTO(
        TicketStatus status,
        TicketPriority priority
) {
}
