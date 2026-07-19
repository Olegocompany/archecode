package com.free.archecode.user.dto.auth.request;

import jakarta.validation.constraints.NotBlank;

public record LoginUserDtoRequest(
        @NotBlank
        String credential,

        @NotBlank
        String password
) { }
