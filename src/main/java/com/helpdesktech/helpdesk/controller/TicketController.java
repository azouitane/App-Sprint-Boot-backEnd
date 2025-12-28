package com.helpdesktech.helpdesk.controller;


import com.helpdesktech.helpdesk.dto.ticket.TicketRequestDTO;
import com.helpdesktech.helpdesk.dto.ticket.TicketResponseDTO;
import com.helpdesktech.helpdesk.dto.ticket.UpdateTicketDTO;
import com.helpdesktech.helpdesk.service.TicketService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;


    // Create ticket
    @PostMapping
    public ResponseEntity<TicketResponseDTO> createTicket(@Valid @RequestBody TicketRequestDTO ticketRequestDTO) {
        TicketResponseDTO response = ticketService.createTicket(ticketRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    // Get all tickets
    @GetMapping
    public List<TicketResponseDTO> getAllTickets() {
        return ticketService.getAllTickets();
    }

    // Get ticket by ID
    @GetMapping("/{id}")
    public ResponseEntity<TicketResponseDTO> getTicketById(@PathVariable UUID id) {
        TicketResponseDTO response = ticketService.getTicketById(id);
        return ResponseEntity.ok(response);
    }

    // Update ticket
    @PutMapping("/{id}")
    public ResponseEntity<TicketResponseDTO> updateTicket(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateTicketDTO updateTicketDTO) {
        TicketResponseDTO response = ticketService.updateTicket(id, updateTicketDTO);
        return ResponseEntity.ok(response);
    }
}
