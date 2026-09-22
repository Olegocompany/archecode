package com.free.archecode.controller.user;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.free.archecode.shared.common.ImageTypeUtils;
import com.free.archecode.user.dto.UserDto;
import com.free.archecode.user.dto.request.FileDto;
import com.free.archecode.user.service.UserService;
import com.free.archecode.utils.user.UserAuthUtils;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserAuthUtils userAuthUtils;

    @Value("${filesystem.bucket-usersAvatars}")
    private String bucketAvatars;

    @GetMapping("/profile")
    public ResponseEntity<UserDto> profile() {
        return ResponseEntity.ok(
                userService.profile(userAuthUtils.getUserAuth()));
    }

    @PostMapping(path = "/avatar", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<Boolean> updateAvatar(@ModelAttribute @Valid FileDto request) {
        userService.updateAvatar(request.getFile(), userAuthUtils.getUserAuth());
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/avatar")
    public ResponseEntity<byte[]> getAvatar() {
        byte[] avatar = userService.getAvatar(userAuthUtils.getUserAuth());
        return ResponseEntity.ok()
                .contentType(ImageTypeUtils.detectMediaType(avatar))
                .cacheControl(CacheControl.maxAge(Duration.ofDays(365))).body(avatar);
    }
}
