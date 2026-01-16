package com.helpdesktech.helpdesk.service.impl;
import com.helpdesktech.helpdesk.dto.glopal.GlopalResponse;
import com.helpdesktech.helpdesk.dto.ticket.MyTickets;
import com.helpdesktech.helpdesk.dto.ticket.TicketRequestDTO;
import com.helpdesktech.helpdesk.dto.ticket.TicketResponseDTO;
import com.helpdesktech.helpdesk.dto.ticket.UpdateTicketDTO;
import com.helpdesktech.helpdesk.entity.Device;
import com.helpdesktech.helpdesk.entity.Ticket;
import com.helpdesktech.helpdesk.entity.User;
import com.helpdesktech.helpdesk.enums.Ticket.TicketStatus;
import com.helpdesktech.helpdesk.exception.ResourceNotFoundException;
import com.helpdesktech.helpdesk.mapper.TicketMapper;
import com.helpdesktech.helpdesk.repository.DeviceRepository;
import com.helpdesktech.helpdesk.repository.TicketRepository;
import com.helpdesktech.helpdesk.repository.UserRepository;
import com.helpdesktech.helpdesk.service.interfaces.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final UserRepository userRepository;
    private final TicketRepository ticketRepository;
    private final DeviceRepository deviceRepository;
    private final TicketMapper ticketMapper;

    @Override
    public GlopalResponse createTicket(TicketRequestDTO ticketRequestDTO) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        UUID userId = UUID.fromString(auth.getName());

        User requester = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + userId));

        Device device = null;
        if (ticketRequestDTO.deviceId() != null) {
            device = deviceRepository.findById(ticketRequestDTO.deviceId())
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Device not found: " + ticketRequestDTO.deviceId()));
        }

        Ticket ticket = ticketMapper.toEntity(ticketRequestDTO);
        ticket.setStatus(TicketStatus.OPEN);
        ticket.setRequester(requester);
        ticket.setDevice(device);

        ticketMapper.toDto(ticketRepository.save(ticket));

        return new GlopalResponse("Ticket created successfully");
    }

    @Override
    public TicketResponseDTO getTicketById(UUID id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket not found: " + id));
        return ticketMapper.toDto(ticket);
    }

    @Override
    public List<TicketResponseDTO> getAllTickets() {
        return ticketRepository.findAll().stream()
                .map(ticketMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public GlopalResponse  updateTicket(UUID id, UpdateTicketDTO updateTicketDTO) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket not found: " + id));
        if (updateTicketDTO.status() != null) ticket.setStatus(updateTicketDTO.status());
        if (updateTicketDTO.priority() != null) ticket.setPriority(updateTicketDTO.priority());
        ticketMapper.toDto(ticketRepository.save(ticket));

        return new  GlopalResponse("Ticket updated successfully");
    }

    @Transactional(readOnly = true)
    @Override
    public List<MyTickets> getTicketsByRequesterId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UUID requesterId = UUID.fromString(auth.getName());

        return ticketRepository.findByRequesterId(requesterId)
                .stream()
                .map(ticket -> new MyTickets(
                        ticket.getId(),
                        ticket.getTitle(),
                        ticket.getDescription(),
                        ticket.getStatus(),
                        ticket.getPriority(),
                        ticket.getCategory(),
                        ticket.getCreatedAt(),
                        ticket.getUpdatedAt(),
                        ticket.getDevice() != null
                                ? new MyTickets.DeviceID(
                                ticket.getDevice().getId(),
                                ticket.getDevice().getName(),
                                ticket.getDevice().getModel(),
                                ticket.getDevice().getManufacturer(),
                                ticket.getDevice().getType(),
                                ticket.getDevice().getSerialNumber(),
                                ticket.getDevice().getStatus(),
                                ticket.getDevice().getPurchaseDate(),
                                ticket.getDevice().getWarrantyExpiry(),
                                ticket.getDevice().getSpecifications()
                        )
                                : null
                ))
                .toList();
    }


}
