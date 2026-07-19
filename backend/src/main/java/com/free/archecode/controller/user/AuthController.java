package com.free.archecode.controller.user;

import com.free.archecode.user.dto.UserMapper;
import com.free.archecode.user.dto.auth.request.LoginUserDtoRequest;
import com.free.archecode.user.dto.auth.request.RegisterUserDtoRequest;
import com.free.archecode.user.dto.auth.response.AuthDtoResponse;
import com.free.archecode.user.dto.auth.response.ContainerAuthDtoResponse;
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
    public ResponseEntity<AuthDtoResponse> register(
            @RequestBody @Valid RegisterUserDtoRequest request,
            HttpServletResponse httpServletResponse
    ) {
        ContainerAuthDtoResponse containerAuthDtoResponse = authService.register(request);
        setTokenToCookie(httpServletResponse, containerAuthDtoResponse.refreshToken());
        return ResponseEntity.ok(userMapper.toAuthResponse(containerAuthDtoResponse.jwtToken()));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthDtoResponse> login(
            @RequestBody @Valid LoginUserDtoRequest request,
            HttpServletResponse httpServletResponse
    ) {
        ContainerAuthDtoResponse containerAuthDtoResponse = authService.login(request);
        setTokenToCookie(httpServletResponse, containerAuthDtoResponse.refreshToken());
        return ResponseEntity.ok(userMapper.toAuthResponse(containerAuthDtoResponse.jwtToken()));
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
        ContainerAuthDtoResponse containerAuthDtoResponse = authService.refreshToken(refreshToken);
        setTokenToCookie(httpServletResponse, containerAuthDtoResponse.refreshToken());
        return ResponseEntity.ok(userMapper.toAuthResponse(containerAuthDtoResponse.jwtToken()));
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
