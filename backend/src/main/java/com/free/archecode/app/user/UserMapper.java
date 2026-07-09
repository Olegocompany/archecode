package com.free.archecode.app.user;

import com.free.archecode.app.user.dto.UserDto;
import org.mapstruct.Mapper;

/*
Интерфейс, с помощью которого Mapper самостоятельно закинет в DTO то, что нужно
 */
@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);
}
