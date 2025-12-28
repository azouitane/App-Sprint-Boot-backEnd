package com.helpdesktech.helpdesk.mapper;

import com.helpdesktech.helpdesk.dto.ticket.TicketRequestDTO;
import com.helpdesktech.helpdesk.dto.ticket.TicketResponseDTO;
import com.helpdesktech.helpdesk.entity.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TicketMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "requester", ignore = true)
    @Mapping(target = "device", ignore = true)
    Ticket toEntity(TicketRequestDTO dto);

    @Mapping(source = "requester.id", target = "requesterId")
    @Mapping(source = "device.id", target = "deviceId")
    TicketResponseDTO toDto(Ticket ticket);
}