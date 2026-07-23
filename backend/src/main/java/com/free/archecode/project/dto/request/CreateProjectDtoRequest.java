package com.free.archecode.project.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CreateProjectDtoRequest(
        @NotNull
        @Size(min = 1)
        @Pattern(regexp = "^[a-zA-Z0-9 ()]*$", message = "only letters, nums and numbers with brackets")
        String name,

        @Pattern(regexp = "^[a-zA-Z0-9 ()]*$", message = "only letters, nums and numbers with brackets")
        String description,

        @NotNull
        @Size(min = 8)
        @Pattern(regexp = "^(github|gitlab)\\.com\\/[a-zA-Z0-9_-]+\\/[a-z0-9\\/_-]+\\.git$", message = "wrong link")
        String link,

        @Pattern(regexp = "^([a-zA-Z0-9]+[./])*[a-zA-Z0-9]+$", message = "wrong name of branch")
        String branch
) { }
