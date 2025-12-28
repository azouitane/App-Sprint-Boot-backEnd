package com.helpdesktech.helpdesk.service.impl;

import com.helpdesktech.helpdesk.dto.device.DeviceRequestDTO;
import com.helpdesktech.helpdesk.dto.device.DeviceResponseDTO;
import com.helpdesktech.helpdesk.entity.Device;
import com.helpdesktech.helpdesk.entity.User;
import com.helpdesktech.helpdesk.enums.Device.DeviceStatus;
import com.helpdesktech.helpdesk.exception.Device.DeviceDuplicateException;
import com.helpdesktech.helpdesk.exception.Device.DeviceNotFoundException;
import com.helpdesktech.helpdesk.exception.ResourceNotFoundException;
import com.helpdesktech.helpdesk.mapper.DeviceMapper;
import com.helpdesktech.helpdesk.repository.DeviceRepository;
import com.helpdesktech.helpdesk.repository.UserRepository;
import com.helpdesktech.helpdesk.service.DeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DeviceServiceImpl implements DeviceService {

    private final DeviceRepository deviceRepository;
    private final UserRepository userRepository;
    private final DeviceMapper deviceMapper;

    @Override
    public DeviceResponseDTO createDevice(DeviceRequestDTO deviceRequestDTO) {
        User user = userRepository.findById(deviceRequestDTO.userId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + deviceRequestDTO.userId()));

        if (deviceRepository.findBySerialNumber(deviceRequestDTO.serialNumber()).isPresent()) {
            throw new DeviceDuplicateException("Device with this serial number already exists");
        }

        Device device = deviceMapper.toEntity(deviceRequestDTO);
        device.setStatus(DeviceStatus.AVAILABLE);
        device.setUser(user);

        Device savedDevice = deviceRepository.save(device);

        return deviceMapper.toDto(savedDevice);
    }


    @Override
    public List<DeviceResponseDTO> getAllDevices() {
        return deviceRepository.findAll()
                .stream()
                .map(deviceMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public DeviceResponseDTO getDeviceById(UUID id) {
        Device device = deviceRepository.findById(id)
                .orElseThrow(() -> new DeviceNotFoundException("Device not found: " + id));
        return deviceMapper.toDto(device);
    }

    @Override
    public DeviceResponseDTO updateDevice(UUID id, DeviceRequestDTO deviceRequestDTO) {
        Device device = deviceRepository.findById(id)
                .orElseThrow(() -> new DeviceNotFoundException("Device not found: " + id));

        if (deviceRequestDTO.name() != null) device.setName(deviceRequestDTO.name());
        if (deviceRequestDTO.model() != null) device.setModel(deviceRequestDTO.model());
        if (deviceRequestDTO.manufacturer() != null) device.setManufacturer(deviceRequestDTO.manufacturer());
        if (deviceRequestDTO.type() != null) device.setType(deviceRequestDTO.type());
        if (deviceRequestDTO.serialNumber() != null) device.setSerialNumber(deviceRequestDTO.serialNumber());
        if (deviceRequestDTO.purchaseDate() != null) device.setPurchaseDate(deviceRequestDTO.purchaseDate());
        if (deviceRequestDTO.warrantyExpiry() != null) device.setWarrantyExpiry(deviceRequestDTO.warrantyExpiry());
        if (deviceRequestDTO.specifications() != null) device.setSpecifications(deviceRequestDTO.specifications());

        if (deviceRequestDTO.userId() != null) {
            User user = userRepository.findById(deviceRequestDTO.userId())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found: " + deviceRequestDTO.userId()));
            device.setUser(user);
        }

        return deviceMapper.toDto(deviceRepository.save(device));
    }

    @Override
    public void deleteDevice(UUID id) {
        Device device = deviceRepository.findById(id)
                .orElseThrow(() -> new DeviceNotFoundException("Device not found: " + id));
        deviceRepository.delete(device);
    }
}
