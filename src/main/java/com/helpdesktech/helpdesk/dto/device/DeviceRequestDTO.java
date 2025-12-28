package com.helpdesktech.helpdesk.dto.device;

import com.helpdesktech.helpdesk.enums.Device.DeviceStatus;
import com.helpdesktech.helpdesk.enums.Device.DeviceType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.UUID;

public record DeviceRequestDTO(
        @NotBlank(message = "Device name is required")
        @Size(max = 100, message = "Device name must be at most 100 characters")
        String name,

        @NotBlank(message = "Model is required")
        @Size(max = 100)
        String model,

        @NotBlank(message = "Manufacturer is required")
        @Size(max = 100)
        String manufacturer,

        @NotNull(message = "Device type is required")
        DeviceType type,

        @NotBlank(message = "Serial number is required")
        @Size(max = 100)
        String serialNumber,

        LocalDate purchaseDate,
        LocalDate warrantyExpiry,

        @NotBlank(message = "Specifications are required")
        @Size(max = 500)
        String specifications,

        UUID userId
) {
}
