package com.free.archecode.user.service;

import com.free.archecode.shared.config.security.user.UserAuthDetails;
import com.free.archecode.user.dto.UserDto;

public interface UserService {
    public UserDto profile(UserAuthDetails userAuthDetails);
    public String uploadAvatar();
}
