package com.helpdesktech.helpdesk.dto.device;

import com.helpdesktech.helpdesk.enums.Device.DeviceStatus;
import com.helpdesktech.helpdesk.enums.Device.DeviceType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record DeviceResponseDTO(
        UUID id,
        String name,
        String model,
        String manufacturer,
        DeviceType type,
        String serialNumber,
        DeviceStatus status,
        LocalDate purchaseDate,
        LocalDate warrantyExpiry,
        String specifications,
        UUID userId
) {
}
