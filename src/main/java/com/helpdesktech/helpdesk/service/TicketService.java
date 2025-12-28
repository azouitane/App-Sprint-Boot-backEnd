package com.helpdesktech.helpdesk.service;

import com.helpdesktech.helpdesk.dto.ticket.TicketRequestDTO;
import com.helpdesktech.helpdesk.dto.ticket.TicketResponseDTO;
import com.helpdesktech.helpdesk.dto.ticket.UpdateTicketDTO;

import java.util.List;
import java.util.UUID;

public interface TicketService {
    TicketResponseDTO createTicket(TicketRequestDTO  ticketRequestDTO);
    TicketResponseDTO getTicketById(UUID id);
    List<TicketResponseDTO> getAllTickets();
    TicketResponseDTO updateTicket(UUID id, UpdateTicketDTO UpdateTicketDTO);
}
