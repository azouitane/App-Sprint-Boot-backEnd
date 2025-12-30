package com.helpdesktech.helpdesk.service.impl;
import com.helpdesktech.helpdesk.dto.dashboard.DashboardDevicesDTO;
import com.helpdesktech.helpdesk.dto.dashboard.DashboardTicketsDTO;
import com.helpdesktech.helpdesk.dto.dashboard.DashboardUsersDTO;
import com.helpdesktech.helpdesk.enums.Device.DeviceStatus;
import com.helpdesktech.helpdesk.enums.Device.DeviceType;
import com.helpdesktech.helpdesk.enums.Ticket.TicketPriority;
import com.helpdesktech.helpdesk.enums.Ticket.TicketStatus;
import com.helpdesktech.helpdesk.enums.User.UserRole;
import com.helpdesktech.helpdesk.enums.User.UserStatus;
import com.helpdesktech.helpdesk.repository.DeviceRepository;
import com.helpdesktech.helpdesk.repository.TicketRepository;
import com.helpdesktech.helpdesk.repository.UserRepository;
import com.helpdesktech.helpdesk.service.interfaces.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {
    private final TicketRepository ticketRepository;
    private final DeviceRepository deviceRepository;
    private final UserRepository userRepository;


    @Override
    public DashboardUsersDTO getDashboardUsers() {
        long totalUsers = userRepository.count();

        DashboardUsersDTO.UserByRoleDTO userByRole = new DashboardUsersDTO.UserByRoleDTO(
                userRepository.countByRole(UserRole.ADMIN),
                userRepository.countByRole(UserRole.TECHNICIAN),
                userRepository.countByRole(UserRole.USER)
        );

        DashboardUsersDTO.UserStatusDTO userStatus = new DashboardUsersDTO.UserStatusDTO(
                userRepository.countByStatus(UserStatus.ACTIVE),
                userRepository.countByStatus(UserStatus.INACTIVE),
                userRepository.countByStatus(UserStatus.SUSPENDED)
        );

        return new DashboardUsersDTO(totalUsers, userByRole, userStatus);
    }

    @Override
    public DashboardTicketsDTO getDashboardTickets() {
        long totalTickets = ticketRepository.count();

        // Count by priority
        long low = ticketRepository.countByPriority(TicketPriority.LOW);
        long medium = ticketRepository.countByPriority(TicketPriority.MEDIUM);
        long high = ticketRepository.countByPriority(TicketPriority.HIGH);
        long critical = ticketRepository.countByPriority(TicketPriority.CRITICAL);

        DashboardTicketsDTO.TicketPriorityDTO ticketPriorityDTO =
                new DashboardTicketsDTO.TicketPriorityDTO(low, medium, high, critical);

        // Count by status
        long open = ticketRepository.countByStatus(TicketStatus.OPEN);
        long inProgress = ticketRepository.countByStatus(TicketStatus.IN_PROGRESS);
        long resolved = ticketRepository.countByStatus(TicketStatus.RESOLVED);
        long closed = ticketRepository.countByStatus(TicketStatus.CLOSED);

        DashboardTicketsDTO.TicketStatusDTO ticketStatusDTO =
                new DashboardTicketsDTO.TicketStatusDTO(open, inProgress, resolved, closed);

        return new DashboardTicketsDTO(totalTickets, ticketPriorityDTO, ticketStatusDTO);
    }

    @Override
    public DashboardDevicesDTO getDashboardDevices() {
        long totalDevices = deviceRepository.count();

        DashboardDevicesDTO.DevicesByStatusDTO devicesByStatus =
                new DashboardDevicesDTO.DevicesByStatusDTO(
                        deviceRepository.countByStatus(DeviceStatus.AVAILABLE),
                        deviceRepository.countByStatus(DeviceStatus.IN_USE),
                        deviceRepository.countByStatus(DeviceStatus.MAINTENANCE),
                        deviceRepository.countByStatus(DeviceStatus.RETIRED)
                );

        DashboardDevicesDTO.DevicesByTypeDTO devicesByType =
                new DashboardDevicesDTO.DevicesByTypeDTO(
                        deviceRepository.countByType(DeviceType.LAPTOP),
                        deviceRepository.countByType(DeviceType.DESKTOP),
                        deviceRepository.countByType(DeviceType.PHONE),
                        deviceRepository.countByType(DeviceType.TABLET),
                        deviceRepository.countByType(DeviceType.SERVER),
                        deviceRepository.countByType(DeviceType.SWITCH),
                        deviceRepository.countByType(DeviceType.PRINTER),
                        deviceRepository.countByType(DeviceType.ACCESS_POINT),
                        deviceRepository.countByType(DeviceType.OTHER)
                );

        return new DashboardDevicesDTO(totalDevices, devicesByStatus, devicesByType);
    }

}
