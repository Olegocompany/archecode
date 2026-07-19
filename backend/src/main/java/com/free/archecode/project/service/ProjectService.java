package com.free.archecode.project.service;

import com.free.archecode.project.ProjectRepository;
import com.free.archecode.project.dto.ProjectMapper;
import com.free.archecode.project.dto.response.forUser.ProjectsOfUserDtoResponse;
import com.free.archecode.shared.config.security.user.ImpUserAuthDetails;
import com.free.archecode.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;
    private final UserRepository userRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository, ProjectMapper projectMapper, UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.projectMapper = projectMapper;
        this.userRepository = userRepository;
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

    public boolean createProject()
    {
        return true;
    }

}
