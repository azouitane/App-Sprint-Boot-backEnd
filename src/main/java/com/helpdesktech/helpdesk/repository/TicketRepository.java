package com.helpdesktech.helpdesk.repository;

import com.helpdesktech.helpdesk.entity.Ticket;
import com.helpdesktech.helpdesk.enums.Ticket.TicketPriority;
import com.helpdesktech.helpdesk.enums.Ticket.TicketStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, UUID> {
    long countByStatus(TicketStatus ticketStatus);
    long countByPriority(TicketPriority ticketPriority);
    List<Ticket> findByRequesterId(UUID requesterId);
    long countByRequesterId(UUID requesterId);
    long countByRequesterIdAndStatus(UUID requesterId, TicketStatus status);
    long countByRequesterIdAndPriority(UUID requesterId, TicketPriority priority);
}
