package com.helpdesktech.helpdesk.dto.dashboard;

public record DashboardTicketsDTO(
        long TotalTickets,
        TicketPriorityDTO TicketPriority,   // LOW, MEDIUM, HIGH, CRITICAL
        TicketStatusDTO TicketStatus        // OPEN, IN_PROGRESS, RESOLVED, CLOSED
) {

    public record TicketPriorityDTO(
            long Low,
            long Medium,
            long High,
            long Critical
    ){}

    public record TicketStatusDTO(
            long Open,
            long In_Progress,
            long Repogress,
            long Closed
    ){}
}

