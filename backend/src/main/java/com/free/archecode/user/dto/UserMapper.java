package com.free.archecode.user.dto;

import com.free.archecode.user.User;
import com.free.archecode.user.dto.auth.request.RegisterUserDtoRequest;
import com.free.archecode.user.dto.auth.response.AuthDtoResponse;
import com.free.archecode.user.dto.auth.response.ContainerAuthDtoResponse;
import com.free.archecode.user.dto.request.UpdateUserDtoRequest;
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
    User toEntity(RegisterUserDtoRequest userDto);

    void update(UpdateUserDtoRequest updateUserDtoRequest, @MappingTarget User user); // для обновы. прием с updateUserRequest и указание кого обновлять

    ContainerAuthDtoResponse toAuthResponse(String jwtToken, String refreshToken);
    AuthDtoResponse toAuthResponse(String token);
}