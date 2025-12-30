package com.helpdesktech.helpdesk.dto.dashboard;

public record DashboardDevicesDTO(
        long TotalDevices,
        DevicesByStatusDTO DevicesByStatus,
        DevicesByTypeDTO DevicesByType
) {

    public record DevicesByStatusDTO(
            long Available,
            long In_Use,
            long Maintenance,
            long Retired
    ) {}

    public record DevicesByTypeDTO(
            long Laptop,
            long Desktop,
            long Phone,
            long Tablet,
            long Server,
            long SwitchDevice,
            long Printer,
            long AccessPoint,
            long Other
    ) {}
}
