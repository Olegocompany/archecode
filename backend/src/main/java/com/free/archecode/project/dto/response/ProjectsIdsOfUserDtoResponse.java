package com.free.archecode.project.dto.response;

import java.util.List;

public record ProjectsIdsOfUserDtoResponse(
        List<Long> projects
) {}
