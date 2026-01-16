package com.helpdesktech.helpdesk.dto.dashboard;

public record DashboardDevicesDTO(
        long totalDevices,
        DevicesByStatusDTO devicesByStatus,
        DevicesByTypeDTO devicesByType
) {

    public record DevicesByStatusDTO(
            long available,
            long inUse,
            long maintenance,
            long retired
    ) {}

    public record DevicesByTypeDTO(
            long laptop,
            long desktop,
            long phone,
            long tablet,
            long server,
            long switchDevice,
            long printer,
            long accessPoint,
            long other
    ) {}
}
