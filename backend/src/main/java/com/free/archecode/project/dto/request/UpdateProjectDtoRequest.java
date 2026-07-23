package com.free.archecode.project.dto.request;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UpdateProjectDtoRequest(

        @Pattern(regexp = "^[a-zA-Z0-9 ()]*$", message = "only letters, nums and numbers with brackets")
        String name,

        @Pattern(regexp = "^[a-zA-Z0-9 ()]*$", message = "only letters, nums and numbers with brackets")
        String description,

        @Size(min = 8)
        @Pattern(regexp = "^(github|gitlab)\\.com\\/[a-zA-Z0-9_-]+\\/[a-z0-9\\/]+\\.git$", message = "wrong link")
        String link,

        @Pattern(regexp = "^([a-zA-Z0-9]+[./])*[a-zA-Z0-9]+$", message = "wrong name of branch")
        String branch
) {
}
