package com.free.archecode.user.dto.auth.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class RegisterUserDtoRequest {

    @Email(message = "wrong format")
    @NotBlank(message = "email required")
    private String email;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9_.()-]*$")
    private String username;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Zа-яА-ЯёЁ0-9!@№#$%^&*()_+\\-=\\[\\]{}|\\\\;:'\",.<>/?`~]+$")
    private String password;
    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    @NotBlank
    @JsonProperty("role_name")
    private String role_name;
}
