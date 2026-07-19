package com.free.archecode.project.dto;


import com.free.archecode.project.Project;
import com.free.archecode.project.dto.response.forUser.ProjectOfUserDtoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProjectMapper {
    ProjectOfUserDtoResponse toDto(Project project);

    @Mapping(target = "id", ignore = true)
    Project toEntity(ProjectOfUserDtoResponse dto);

    void updateProject(ProjectOfUserDtoResponse dto, @MappingTarget Project project);
}
