package com.helpdesktech.helpdesk.service.impl;
import com.helpdesktech.helpdesk.dto.dashboard.DashboardByUserDTO;
import com.helpdesktech.helpdesk.enums.Device.DeviceStatus;
import com.helpdesktech.helpdesk.enums.Device.DeviceType;
import com.helpdesktech.helpdesk.enums.Ticket.TicketPriority;
import com.helpdesktech.helpdesk.enums.Ticket.TicketStatus;
import com.helpdesktech.helpdesk.repository.DeviceRepository;
import com.helpdesktech.helpdesk.repository.TicketRepository;
import com.helpdesktech.helpdesk.service.interfaces.DashboardByUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DashboardByUserServiceImpl implements DashboardByUserService {

    private final TicketRepository ticketRepository;
    private final DeviceRepository deviceRepository;
    @Override
    public DashboardByUserDTO getMyDashboard() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UUID userId = UUID.fromString(auth.getName());

        return new DashboardByUserDTO(
                ticketRepository.countByRequesterId(userId),

                new DashboardByUserDTO.TicketsByStatusDTO(
                        ticketRepository.countByRequesterIdAndStatus(userId, TicketStatus.OPEN),
                        ticketRepository.countByRequesterIdAndStatus(userId, TicketStatus.IN_PROGRESS),
                        ticketRepository.countByRequesterIdAndStatus(userId, TicketStatus.RESOLVED),
                        ticketRepository.countByRequesterIdAndStatus(userId, TicketStatus.CLOSED)
                ),

                new DashboardByUserDTO.TicketsByPriorityDTO(
                        ticketRepository.countByRequesterIdAndPriority(userId, TicketPriority.LOW),
                        ticketRepository.countByRequesterIdAndPriority(userId, TicketPriority.MEDIUM),
                        ticketRepository.countByRequesterIdAndPriority(userId, TicketPriority.HIGH),
                        ticketRepository.countByRequesterIdAndPriority(userId, TicketPriority.CRITICAL)
                ),

                new DashboardByUserDTO.DeviceByStatusDTO(
                        deviceRepository.countByUser_IdAndStatus(userId, DeviceStatus.AVAILABLE),
                        deviceRepository.countByUser_IdAndStatus(userId, DeviceStatus.IN_USE),
                        deviceRepository.countByUser_IdAndStatus(userId, DeviceStatus.MAINTENANCE),
                        deviceRepository.countByUser_IdAndStatus(userId, DeviceStatus.RETIRED)
                ),
                new DashboardByUserDTO.DeviceByTypeDTO(
                        deviceRepository.countByUser_IdAndType(userId, DeviceType.LAPTOP),
                        deviceRepository.countByUser_IdAndType(userId, DeviceType.DESKTOP),
                        deviceRepository.countByUser_IdAndType(userId, DeviceType.PHONE),
                        deviceRepository.countByUser_IdAndType(userId, DeviceType.TABLET),
                        deviceRepository.countByUser_IdAndType(userId, DeviceType.SERVER),
                        deviceRepository.countByUser_IdAndType(userId, DeviceType.SWITCH),
                        deviceRepository.countByUser_IdAndType(userId, DeviceType.PRINTER),
                        deviceRepository.countByUser_IdAndType(userId, DeviceType.ACCESS_POINT),
                        deviceRepository.countByUser_IdAndType(userId, DeviceType.OTHER)
                )
        );
    }
}
