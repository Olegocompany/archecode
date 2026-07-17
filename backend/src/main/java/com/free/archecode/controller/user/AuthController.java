package com.free.archecode.controller.user;

import com.free.archecode.user.dto.UserMapper;import com.free.archecode.user.dto.auth.request.LoginUserRequest;
import com.free.archecode.user.dto.auth.request.RegisterUserRequest;
import com.free.archecode.user.dto.auth.response.AuthResponse;
import com.free.archecode.user.dto.auth.response.ContainerAuthResponse;import com.free.archecode.user.service.AuthService;
import jakarta.servlet.http.Cookie;import jakarta.servlet.http.HttpServletResponse;import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final static String cookieName = "token2";private final UserMapper userMapper;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(
            @RequestBody @Valid RegisterUserRequest request,
            HttpServletResponse httpServletResponse
    ) {
        ContainerAuthResponse containerAuthResponse = authService.register(request);
        httpServletResponse.addCookie(new Cookie(cookieName, containerAuthResponse.refreshToken()));
        return ResponseEntity.ok(userMapper.toAuthResponse(containerAuthResponse.jwtToken()));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @RequestBody @Valid LoginUserRequest request,
            HttpServletResponse httpServletResponse
    ) {
        ContainerAuthResponse containerAuthResponse = authService.login(request);
        httpServletResponse.addCookie(new Cookie(cookieName, containerAuthResponse.refreshToken()));
        return ResponseEntity.ok(userMapper.toAuthResponse(containerAuthResponse.jwtToken()));
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refresh(
            @CookieValue(value = cookieName) String refreshToken,
            HttpServletResponse httpServletResponse
            ) {
        ContainerAuthResponse containerAuthResponse = authService.refreshToken(refreshToken);
        httpServletResponse.addCookie(new Cookie(cookieName, containerAuthResponse.refreshToken()));
        return ResponseEntity.ok(userMapper.toAuthResponse(containerAuthResponse.jwtToken()));
    }
}
