package com.free.archecode.user.dto;

import com.free.archecode.user.User;
import com.free.archecode.user.dto.auth.request.RegisterUserRequest;
import com.free.archecode.user.dto.auth.response.AuthResponse;
import com.free.archecode.user.dto.auth.response.ContainerAuthResponse;
import com.free.archecode.user.dto.request.UpdateUserRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/*
Интерфейс, с помощью которого Mapper самостоятельно закинет в DTO то, что нужно
 */
@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", ignore = true) // игнорируем эту автоподставку, потом в сервисе сами расставим
    User toEntity(RegisterUserRequest userDto);

    void update(UpdateUserRequest updateUserRequest, @MappingTarget User user); // для обновы. прием с updateUserRequest и указание кого обновлять

    ContainerAuthResponse toAuthResponse(String jwtToken,  String refreshToken);
    AuthResponse toAuthResponse(String token);
}