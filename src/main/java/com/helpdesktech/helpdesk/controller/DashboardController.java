package com.helpdesktech.helpdesk.controller;
import com.helpdesktech.helpdesk.dto.dashboard.DashboardDevicesDTO;
import com.helpdesktech.helpdesk.dto.dashboard.DashboardTicketsDTO;
import com.helpdesktech.helpdesk.dto.dashboard.DashboardUsersDTO;
import com.helpdesktech.helpdesk.service.interfaces.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @PreAuthorize("hasAnyRole('ADMIN', 'TECHNICIAN')")
    @GetMapping("users")
    public ResponseEntity<DashboardUsersDTO> getDashboardUsers() {
        return ResponseEntity.ok(dashboardService.getDashboardUsers());
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'TECHNICIAN')")
    @GetMapping("tickets")
    public ResponseEntity<DashboardTicketsDTO> getDashboardTickets() {
        return ResponseEntity.ok(dashboardService.getDashboardTickets());
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'TECHNICIAN')")
    @GetMapping("devices")
    public ResponseEntity<DashboardDevicesDTO> getDashboardDevices() {
        return ResponseEntity.ok(dashboardService.getDashboardDevices());
    }
}