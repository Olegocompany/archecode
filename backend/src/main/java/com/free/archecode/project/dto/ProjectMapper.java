package com.free.archecode.project.dto;


import com.free.archecode.project.Project;
import com.free.archecode.project.dto.request.CreateProjectDtoRequest;
import com.free.archecode.project.dto.request.UpdateProjectDtoRequest;
import com.free.archecode.project.dto.response.ProjectDtoResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ProjectMapper {
    ProjectDtoResponse toDto(Project project);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    Project toEntity(CreateProjectDtoRequest dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE) // ignore NULL variables (patch-like)
    Project updateProject(UpdateProjectDtoRequest dto, @MappingTarget Project project);
}
