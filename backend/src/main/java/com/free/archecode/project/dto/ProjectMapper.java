package com.free.archecode.project.dto;


import com.free.archecode.project.Project;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProjectMapper {
    ProjectDto toDto(Project project);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    Project toEntity(ProjectDto dto);

    void updateProject(ProjectDto dto, @MappingTarget Project project);
}
