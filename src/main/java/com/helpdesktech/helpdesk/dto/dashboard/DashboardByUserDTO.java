package com.helpdesktech.helpdesk.dto.dashboard;

public record DashboardByUserDTO(
        long totalTickets,
        TicketsByStatusDTO ticketsByStatus,
        TicketsByPriorityDTO ticketsByPriority,
        DeviceByStatusDTO deviceByStatus,
        DeviceByTypeDTO deviceByType
) {

    public record TicketsByStatusDTO(
            long open,
            long inProgress,
            long reopened,
            long closed
    ) {}

    public record TicketsByPriorityDTO(
            long low,
            long medium,
            long high,
            long critical
    ) {}

    public record DeviceByStatusDTO(
            long available,
            long inUse,
            long maintenance,
            long retired
    ) {}

    public record DeviceByTypeDTO(
            long laptop,
            long desktop,
            long phone,
            long tablet,
            long server,
            long switchCount,
            long printer,
            long accessPoint,
            long other
    ) {}
}
