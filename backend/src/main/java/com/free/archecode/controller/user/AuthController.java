package com.free.archecode.controller.user;

import com.free.archecode.user.dto.auth.AuthResponse;
import com.free.archecode.user.dto.auth.LoginUserRequest;
import com.free.archecode.user.dto.auth.RegisterUserRequest;
import com.free.archecode.user.service.AuthService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(
            @RequestBody @Valid RegisterUserRequest request
    ) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @RequestBody @Valid LoginUserRequest request
    ) {
        return  ResponseEntity.ok(authService.login(request));
    }
}
