package com.free.archecode.user.service.imp;

import java.time.Duration;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.free.archecode.shared.common.ImageTypeUtils;
import com.free.archecode.shared.config.security.user.UserAuthDetails;
import com.free.archecode.user.dto.UserDto;
import com.free.archecode.user.dto.UserMapper;
import com.free.archecode.user.service.UserService;
import com.free.archecode.utils.filesystem.S3Utils;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserServiceImp implements UserService {

    private final UserMapper userMapper;
    private final S3Utils s3utils;

    @Value("${filesystem.avatars.bucket-name}")
    private final String bucketAvatars;

    @Value("${filesystem.avatars.prefix}")
    private final String avatarPrefix;
    
    public UserDto profile(UserAuthDetails user) {
        return userMapper.toDto(user.getUser());
    }

    public void updateAvatar(MultipartFile file, UserAuthDetails user) {
        try {
            s3utils.uploadFile(avatarPrefix + user.getUserId() + ".jpeg", file.getBytes(), bucketAvatars);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public byte[] getAvatar(UserAuthDetails user) {
        return s3utils.getFile(avatarPrefix + user.getUserId() + ".jpeg", bucketAvatars);
    }

}
