package com.free.archecode.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
