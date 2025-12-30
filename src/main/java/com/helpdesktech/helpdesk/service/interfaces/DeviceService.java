package com.helpdesktech.helpdesk.service.interfaces;

import com.helpdesktech.helpdesk.dto.device.DeviceRequestDTO;
import com.helpdesktech.helpdesk.dto.device.DeviceResponseDTO;
import com.helpdesktech.helpdesk.dto.glopal.GlopalResponse;

import java.util.List;
import java.util.UUID;

public interface DeviceService {
    GlopalResponse createDevice(DeviceRequestDTO deviceRequestDTO);
    List<DeviceResponseDTO> getAllDevices();
    DeviceResponseDTO getDeviceById(UUID id);
    GlopalResponse updateDevice(UUID id, DeviceRequestDTO deviceRequestDTO);
    void deleteDevice(UUID id);
}

