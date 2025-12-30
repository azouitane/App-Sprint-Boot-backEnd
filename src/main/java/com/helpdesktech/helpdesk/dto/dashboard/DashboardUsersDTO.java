package com.helpdesktech.helpdesk.dto.dashboard;

public record DashboardUsersDTO(
        long TotalUsers,
        UserByRoleDTO UserByRole,
        UserStatusDTO UserStatus
) {

    public record UserByRoleDTO(
            long Admins,
            long Aechnicians,
            long Users
    ) {}

    public record UserStatusDTO(
            long Active,
            long Inactive,
            long Suspened
    ) {}
}