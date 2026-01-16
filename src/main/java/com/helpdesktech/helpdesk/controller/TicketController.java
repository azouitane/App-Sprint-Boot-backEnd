package com.helpdesktech.helpdesk.controller;
import com.helpdesktech.helpdesk.dto.glopal.GlopalResponse;
import com.helpdesktech.helpdesk.dto.ticket.MyTickets;
import com.helpdesktech.helpdesk.dto.ticket.TicketRequestDTO;
import com.helpdesktech.helpdesk.dto.ticket.TicketResponseDTO;
import com.helpdesktech.helpdesk.dto.ticket.UpdateTicketDTO;
import com.helpdesktech.helpdesk.service.interfaces.TicketService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @PreAuthorize("hasAnyRole('ADMIN', 'TECHNICIAN','USER')")
    // Create ticket
    @PostMapping
    public ResponseEntity<GlopalResponse> createTicket(@Valid @RequestBody TicketRequestDTO ticketRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ticketService.createTicket(ticketRequestDTO));
    }


    // Get all tickets
    @PreAuthorize("hasAnyRole('ADMIN', 'TECHNICIAN')")
    @GetMapping
    public List<TicketResponseDTO> getAllTickets() {
        return ticketService.getAllTickets();
    }

    // Get ticket by ID
    @PreAuthorize("hasAnyRole('ADMIN', 'TECHNICIAN')")
    @GetMapping("/{id}")
    public ResponseEntity<TicketResponseDTO> getTicketById(@PathVariable UUID id) {
        return ResponseEntity.ok(ticketService.getTicketById(id));
    }


    // Update ticket
    @PreAuthorize("hasAnyRole('ADMIN', 'TECHNICIAN')")
    @PutMapping("/{id}")
    public ResponseEntity<GlopalResponse> updateTicket(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateTicketDTO updateTicketDTO) {
        return ResponseEntity.ok(ticketService.updateTicket(id, updateTicketDTO));
    }

    @PreAuthorize("hasAnyRole('ADMIN','TECHNICIAN','USER')")
    @GetMapping("/my")
    public ResponseEntity<List<MyTickets>> getMyTickets() {
        return ResponseEntity.ok(ticketService.getTicketsByRequesterId());
    }
}
