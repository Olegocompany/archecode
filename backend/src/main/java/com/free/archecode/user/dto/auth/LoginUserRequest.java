package com.free.archecode.user.dto.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginUserRequest {
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
