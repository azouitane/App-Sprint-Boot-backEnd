package com.helpdesktech.helpdesk.mapper;

import com.helpdesktech.helpdesk.dto.device.DeviceRequestDTO;
import com.helpdesktech.helpdesk.dto.device.DeviceResponseDTO;
import com.helpdesktech.helpdesk.entity.Device;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DeviceMapper {

    DeviceMapper INSTANCE = Mappers.getMapper(DeviceMapper.class);

    // DeviceRequestDTO -> Device
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "tickets", ignore = true)
    Device toEntity(DeviceRequestDTO dto);

    // Device -> DeviceResponseDTO
    @Mapping(target = "userId", source = "user.id")
    DeviceResponseDTO toDto(Device device);
}
