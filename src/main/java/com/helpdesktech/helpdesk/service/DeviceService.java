package com.helpdesktech.helpdesk.service;

import com.helpdesktech.helpdesk.dto.device.DeviceRequestDTO;
import com.helpdesktech.helpdesk.dto.device.DeviceResponseDTO;

import java.util.List;
import java.util.UUID;

public interface DeviceService {
    DeviceResponseDTO createDevice(DeviceRequestDTO deviceRequestDTO);
    List<DeviceResponseDTO> getAllDevices();
    DeviceResponseDTO getDeviceById(UUID id);
    DeviceResponseDTO updateDevice(UUID id, DeviceRequestDTO deviceRequestDTO);
    void deleteDevice(UUID id);
}

