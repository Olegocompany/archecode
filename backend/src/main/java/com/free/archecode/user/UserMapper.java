package com.free.archecode.user;

import com.free.archecode.user.dto.auth.RegisterUserRequest;
import com.free.archecode.user.dto.UpdateUserRequest;
import com.free.archecode.user.dto.UserDto;
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
}
