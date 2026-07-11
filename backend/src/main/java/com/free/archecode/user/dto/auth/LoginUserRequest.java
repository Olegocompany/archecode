package com.free.archecode.user.dto.auth;

import lombok.Data;

@Data
public class LoginUserRequest {
    private String email;
    private String password;
}
