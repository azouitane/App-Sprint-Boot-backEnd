package com.helpdesktech.helpdesk.controller;
import com.helpdesktech.helpdesk.dto.dashboard.DashboardByUserDTO;
import com.helpdesktech.helpdesk.service.interfaces.DashboardByUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard/user")
@RequiredArgsConstructor
public class DashboardByUserController {
    private final DashboardByUserService dashboardByUserService;
    @PreAuthorize("hasAnyRole('USER','ADMIN','TECHNICIAN')")
    @GetMapping
    public ResponseEntity<DashboardByUserDTO> getMyDashboard() {
        return ResponseEntity.ok(dashboardByUserService.getMyDashboard());
    }
}
