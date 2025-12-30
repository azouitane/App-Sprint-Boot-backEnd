package com.helpdesktech.helpdesk.repository;

import com.helpdesktech.helpdesk.entity.Device;
import com.helpdesktech.helpdesk.enums.Device.DeviceStatus;
import com.helpdesktech.helpdesk.enums.Device.DeviceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DeviceRepository extends JpaRepository<Device, UUID> {
    Optional<Device> findBySerialNumber(String serialNumber);
    long countByStatus(DeviceStatus deviceStatus);
    long countByType(DeviceType deviceType);
}
