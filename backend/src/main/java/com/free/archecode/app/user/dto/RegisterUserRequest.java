package com.free.archecode.app.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.free.archecode.app.role.Role;
import lombok.Data;

@Data
public class RegisterUserRequest {
    private String email;
    private String password;
    private String name;
    private String surname;
    @JsonProperty("role_id")
    private Long role;
}
