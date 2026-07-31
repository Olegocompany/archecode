package com.free.archecode.user.service.imp;

import org.springframework.stereotype.Service;

import com.free.archecode.shared.config.security.user.UserAuthDetails;
import com.free.archecode.user.dto.UserDto;
import com.free.archecode.user.dto.UserMapper;
import com.free.archecode.user.service.UserService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserServiceImp implements UserService {

    private final UserMapper userMapper;

    public UserDto profile(UserAuthDetails user) {
        return userMapper.toDto(user.getUser());
    }

    public String uploadAvatar() {
        return "sas";
    }

}
