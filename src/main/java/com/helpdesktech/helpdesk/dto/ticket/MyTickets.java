package com.helpdesktech.helpdesk.dto.ticket;

import com.helpdesktech.helpdesk.enums.Device.DeviceStatus;
import com.helpdesktech.helpdesk.enums.Device.DeviceType;
import com.helpdesktech.helpdesk.enums.Ticket.TicketPriority;
import com.helpdesktech.helpdesk.enums.Ticket.TicketStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record MyTickets(
        UUID id,
        String title,
        String description,
        TicketStatus status,
        TicketPriority priority,
        String category,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        DeviceID device
) {
    public record DeviceID(
            UUID id,
            String name,
            String model,
            String manufacturer,
            DeviceType type,
            String serialNumber,
            DeviceStatus status,
            LocalDate purchaseDate,
            LocalDate warrantyExpiry,
            String specifications
    ){

    }
}
