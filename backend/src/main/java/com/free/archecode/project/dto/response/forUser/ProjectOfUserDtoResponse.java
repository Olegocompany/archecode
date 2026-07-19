package com.free.archecode.project.dto.response.forUser;

import jakarta.validation.constraints.NotNull;

public record ProjectOfUserDtoResponse(
        String name,

        String description,

        @NotNull
        String link,

        String branch
) { }
