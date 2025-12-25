package com.helpdesktech.helpdesk.mapper;


import com.helpdesktech.helpdesk.dto.user.UserRequestDTO;
import com.helpdesktech.helpdesk.dto.user.UserResponseDTO;
import com.helpdesktech.helpdesk.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(UserRequestDTO dto);
    UserResponseDTO toDto(User user);
}
