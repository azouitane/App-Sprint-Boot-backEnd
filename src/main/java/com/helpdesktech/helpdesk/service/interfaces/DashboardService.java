package com.helpdesktech.helpdesk.service.interfaces;

import com.helpdesktech.helpdesk.dto.dashboard.DashboardDevicesDTO;
import com.helpdesktech.helpdesk.dto.dashboard.DashboardTicketsDTO;
import com.helpdesktech.helpdesk.dto.dashboard.DashboardUsersDTO;

public interface DashboardService {
    DashboardUsersDTO getDashboardUsers();
    DashboardTicketsDTO getDashboardTickets();
    DashboardDevicesDTO getDashboardDevices();
}
