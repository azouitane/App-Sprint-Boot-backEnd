package com.helpdesktech.helpdesk.controller;

import com.helpdesktech.helpdesk.dto.device.DeviceRequestDTO;
import com.helpdesktech.helpdesk.dto.device.DeviceResponseDTO;
import com.helpdesktech.helpdesk.service.DeviceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/devices")
@RequiredArgsConstructor
public class DeviceController {
    private final DeviceService deviceService;

    @PreAuthorize("hasAnyRole('ADMIN', 'TECHNICIAN')")
    // Create Devicebvgvnhhhhbhhbhhghbbgghghbhb
    @PostMapping
    public ResponseEntity<DeviceResponseDTO> createDevice(@Valid @RequestBody DeviceRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(deviceService.createDevice(dto));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'TECHNICIAN')")
    // Get all Devices
    @GetMapping
    public ResponseEntity<List<DeviceResponseDTO>> getAllDevices() {
        return ResponseEntity.ok(deviceService.getAllDevices());
    }

    // Get Device by ID
    @PreAuthorize("hasAnyRole('ADMIN', 'TECHNICIAN')")
    @GetMapping("/{id}")
    public ResponseEntity<DeviceResponseDTO> getDeviceById(@PathVariable UUID id) {
        return ResponseEntity.ok(deviceService.getDeviceById(id));
    }

    // Update Device
    @PreAuthorize("hasAnyRole('ADMIN', 'TECHNICIAN')")
    @PutMapping("/{id}")
    public ResponseEntity<DeviceResponseDTO> updateDevice(
            @PathVariable UUID id,
            @Valid @RequestBody DeviceRequestDTO dto) {
        return ResponseEntity.ok(deviceService.updateDevice(id, dto));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'TECHNICIAN')")
    // Delete Device
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDevice(@PathVariable UUID id) {
        deviceService.deleteDevice(id);
        return ResponseEntity.noContent().build();
    }
}
