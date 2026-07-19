package com.free.archecode.project.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ProjectDto(
        @NotNull
        @Size(min = 1)
        @Pattern(regexp = "^[a-zA-Z0-9 ()]*$")
        String name,

        @Pattern(regexp = "^[a-zA-Z0-9 ()]*$")
        String description,

        @NotNull
        @Size(min = 8)
        @Pattern(regexp = "^([a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,}\\/[a-z0-9\\/]+\\.git$")
        String link,

        @Pattern(regexp = "^([a-zA-Z0-9]+[./])*[a-zA-Z0-9]+$")
        String branch
) { }
