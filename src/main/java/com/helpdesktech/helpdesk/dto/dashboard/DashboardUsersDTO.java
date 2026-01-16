package com.helpdesktech.helpdesk.dto.dashboard;

public record DashboardUsersDTO(
        long totalUsers,
        UserByRoleDTO userByRole,
        UserStatusDTO userStatus
) {

    public record UserByRoleDTO(
            long admins,
            long technicians,
            long users
    ) {}

    public record UserStatusDTO(
            long active,
            long inactive,
            long suspended
    ) {}
}
