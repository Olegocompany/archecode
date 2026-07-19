package com.free.archecode.project.service;

import com.free.archecode.project.Project;
import com.free.archecode.project.ProjectRepository;
import com.free.archecode.project.dto.ProjectDto;
import com.free.archecode.project.dto.ProjectMapper;
import com.free.archecode.project.dto.response.ProjectsOfUserDtoResponse;
import com.free.archecode.shared.config.security.user.ImpUserAuthDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;

    @Autowired
    public ProjectService(ProjectRepository projectRepository, ProjectMapper projectMapper) {
        this.projectRepository = projectRepository;
        this.projectMapper = projectMapper;
    }

    public ProjectsOfUserDtoResponse getProjectsOfUser(ImpUserAuthDetails user)
    {
        if (user == null) {
            return null;
        }
        return new ProjectsOfUserDtoResponse(
                projectRepository.findProjectsByUserId(user.getUserId())
                .stream().map(projectMapper::toDto)
                .toList()
        );
    }

    public ProjectDto createProject(ProjectDto data)
    {
        try {
            ImpUserAuthDetails user = (ImpUserAuthDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Project project = projectMapper.toEntity(data);
            project.setUser(user.getUser());
            projectRepository.save(project);
            return projectMapper.toDto(project);
        } catch (NullPointerException e) {
            throw new UsernameNotFoundException("user not found");
        }
    }

}
