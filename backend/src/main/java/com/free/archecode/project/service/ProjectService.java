package com.free.archecode.project.service;

import com.free.archecode.project.dto.request.CreateProjectDtoRequest;
import com.free.archecode.project.dto.request.UpdateProjectDtoRequest;
import com.free.archecode.project.dto.response.ProjectDtoResponse;
import com.free.archecode.project.dto.response.ProjectsDetailsOfUserDtoResponse;
import com.free.archecode.shared.config.security.user.UserAuthDetailsImp;

public interface ProjectService {
    public ProjectsDetailsOfUserDtoResponse getProjectsOfUser(UserAuthDetailsImp user);
    public ProjectDtoResponse getProjectByIdOfUserById(UserAuthDetailsImp user, Long projectId);
    public ProjectDtoResponse createProject(CreateProjectDtoRequest data, UserAuthDetailsImp userAuthDetails);
    public ProjectDtoResponse updateProject(UserAuthDetailsImp userAuthDetails, Long projectId, UpdateProjectDtoRequest data);
    public boolean deleteProjectById(Long projectId, Long userId);
}
