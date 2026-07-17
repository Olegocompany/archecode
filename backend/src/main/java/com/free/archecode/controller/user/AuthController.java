package com.free.archecode.controller.user;

import com.free.archecode.user.dto.UserMapper;
import com.free.archecode.user.dto.auth.request.LoginUserRequest;
import com.free.archecode.user.dto.auth.request.RegisterUserRequest;
import com.free.archecode.user.dto.auth.response.AuthResponse;
import com.free.archecode.user.dto.auth.response.ContainerAuthResponse;
import com.free.archecode.user.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final UserMapper userMapper;

    private final static String cookieName = "rtoken";

    @Value("${jwt.refresh.expiration}")
    private long expiration;

    public AuthController(AuthService authService, UserMapper userMapper) {
        this.authService = authService;
        this.userMapper = userMapper;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(
            @RequestBody @Valid RegisterUserRequest request,
            HttpServletResponse httpServletResponse
    ) {
        ContainerAuthResponse containerAuthResponse = authService.register(request);
        setTokenToCookie(httpServletResponse, containerAuthResponse.refreshToken());
        return ResponseEntity.ok(userMapper.toAuthResponse(containerAuthResponse.jwtToken()));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @RequestBody @Valid LoginUserRequest request,
            HttpServletResponse httpServletResponse
    ) {
        ContainerAuthResponse containerAuthResponse = authService.login(request);
        setTokenToCookie(httpServletResponse, containerAuthResponse.refreshToken());
        return ResponseEntity.ok(userMapper.toAuthResponse(containerAuthResponse.jwtToken()));
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(
            @CookieValue(name = cookieName, required = true, defaultValue = "") String refreshToken,
            HttpServletRequest  httpServletRequest,
            HttpServletResponse httpServletResponse
            ) {
        if (refreshToken == null) {
            return ResponseEntity.badRequest().build();
        }
        System.out.println(refreshToken);
        ContainerAuthResponse containerAuthResponse = authService.refreshToken(refreshToken);
        setTokenToCookie(httpServletResponse, containerAuthResponse.refreshToken());
        return ResponseEntity.ok(userMapper.toAuthResponse(containerAuthResponse.jwtToken()));
    }

    private void setTokenToCookie(HttpServletResponse httpServletResponse, String token) {
        ResponseCookie cookie = ResponseCookie.from(cookieName, token)
            .httpOnly(true)
            .secure(true)
            .maxAge(expiration/1000)
            .path("/auth/refresh")
            .sameSite("Strict")
            .build();

        httpServletResponse.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
    }
}
