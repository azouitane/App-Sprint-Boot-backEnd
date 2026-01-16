package com.helpdesktech.helpdesk.dto.dashboard;

public record DashboardTicketsDTO(
        long totalTickets,
        TicketPriorityDTO ticketPriority,
        TicketStatusDTO ticketStatus
) {

    public record TicketPriorityDTO(
            long low,
            long medium,
            long high,
            long critical
    ) {}

    public record TicketStatusDTO(
            long open,
            long inProgress,
            long reopened,
            long closed
    ) {}
}
