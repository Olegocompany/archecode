package com.free.archecode.user.dto.auth.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginUserDtoRequest {
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
