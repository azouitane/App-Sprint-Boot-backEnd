package com.helpdesktech.helpdesk.service.interfaces;

import com.helpdesktech.helpdesk.dto.glopal.GlopalResponse;
import com.helpdesktech.helpdesk.dto.ticket.MyTickets;
import com.helpdesktech.helpdesk.dto.ticket.TicketRequestDTO;
import com.helpdesktech.helpdesk.dto.ticket.TicketResponseDTO;
import com.helpdesktech.helpdesk.dto.ticket.UpdateTicketDTO;

import java.util.List;
import java.util.UUID;

public interface TicketService {
    GlopalResponse createTicket(TicketRequestDTO  ticketRequestDTO);
    TicketResponseDTO getTicketById(UUID id);
    List<TicketResponseDTO> getAllTickets();
    GlopalResponse updateTicket(UUID id, UpdateTicketDTO UpdateTicketDTO);
    public List<MyTickets> getTicketsByRequesterId();
}
