package com.free.archecode.project.service;

import com.free.archecode.project.dto.request.CreateProjectDtoRequest;
import com.free.archecode.project.dto.request.UpdateProjectDtoRequest;
import com.free.archecode.project.dto.response.ProjectDtoResponse;
import com.free.archecode.project.dto.response.ProjectsDetailsOfUserDtoResponse;
import com.free.archecode.shared.config.security.user.UserAuthDetails;

public interface ProjectService {
    public ProjectsDetailsOfUserDtoResponse getProjectsOfUser(UserAuthDetails user);
    public ProjectDtoResponse getProjectByIdOfUserById(UserAuthDetails user, Long projectId);
    public ProjectDtoResponse createProject(CreateProjectDtoRequest data, UserAuthDetails userAuthDetails);
    public ProjectDtoResponse updateProject(UserAuthDetails userAuthDetails, Long projectId, UpdateProjectDtoRequest data);
    public boolean deleteProjectById(Long projectId, Long userId);
}
