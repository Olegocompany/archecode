package com.free.archecode.controller.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.free.archecode.user.dto.UserDto;
import com.free.archecode.user.service.UserService;
import com.free.archecode.utils.user.UserAuthUtils;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserAuthUtils userAuthUtils;
    

    @GetMapping("/profile")
    public ResponseEntity<UserDto> profile() {
        return ResponseEntity.ok(
                userService.profile(userAuthUtils.getUserAuth())
        );
    }
}
