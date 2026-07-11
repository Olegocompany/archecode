package com.free.archecode.user.dto.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;import jakarta.validation.constraints.Min;import jakarta.validation.constraints.NotBlank;import lombok.Data;

@Data
public class RegisterUserRequest {

    @Email(message = "wrong format")
    @NotBlank(message = "email required")
    private String email;

    @NotBlank
    private String password;
    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    @NotBlank
    @JsonProperty("role_name")
    private String role_name;
}
