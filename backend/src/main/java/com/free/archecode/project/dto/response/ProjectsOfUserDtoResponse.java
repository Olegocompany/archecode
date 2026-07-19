package com.free.archecode.project.dto.response;

import com.free.archecode.project.dto.ProjectDto;

import java.util.List;

public record ProjectsOfUserDtoResponse(List<ProjectDto> projects) {
}
