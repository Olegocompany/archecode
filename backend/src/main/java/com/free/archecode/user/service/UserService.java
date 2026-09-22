package com.free.archecode.user.service;

import org.springframework.web.multipart.MultipartFile;

import com.free.archecode.shared.config.security.user.UserAuthDetails;
import com.free.archecode.user.dto.UserDto;

public interface UserService {
    public UserDto profile(UserAuthDetails userAuthDetails);

    public void updateAvatar(MultipartFile file, UserAuthDetails user);

    public byte[] getAvatar(UserAuthDetails user);
}
