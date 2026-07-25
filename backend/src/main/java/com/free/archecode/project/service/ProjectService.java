package com.free.archecode.project.service;

import com.free.archecode.project.Project;
import com.free.archecode.project.ProjectRepository;
import com.free.archecode.project.dto.ProjectMapper;
import com.free.archecode.project.dto.request.CreateProjectDtoRequest;
import com.free.archecode.project.dto.request.UpdateProjectDtoRequest;
import com.free.archecode.project.dto.response.ProjectDtoResponse;
import com.free.archecode.project.dto.response.ProjectsDetailsOfUserDtoResponse;
import com.free.archecode.shared.common.exceptions.NotFoundException;
import com.free.archecode.shared.common.exceptions.project.CantFindGitProjectException;
import com.free.archecode.shared.common.exceptions.project.UserHasTooManyProjects;
import com.free.archecode.shared.config.security.user.ImpUserAuthDetails;
import com.free.archecode.user.UserRepository;
import com.free.archecode.utils.GitUtils;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;
    private final UserRepository userRepository;
    private final GitUtils gitUtils;

    @Autowired
    public ProjectService(ProjectRepository projectRepository, ProjectMapper projectMapper, UserRepository userRepository, GitUtils gitUtils) {
        this.projectRepository = projectRepository;
        this.projectMapper = projectMapper;
        this.userRepository = userRepository;
        this.gitUtils = gitUtils;
    }

    public ProjectsDetailsOfUserDtoResponse getProjectsOfUser(ImpUserAuthDetails user)
    {
        if (user == null) {
            return null;
        }
        return new ProjectsDetailsOfUserDtoResponse(
                projectRepository.findProjectsByUserId(user.getUserId())
                .stream().map(projectMapper::toDto)
                .toList()
        );
    }

    public ProjectDtoResponse getProjectByIdOfUserById(ImpUserAuthDetails user, Long projectId) {
        if (user == null) {
            throw new NullPointerException();
        }
        Project project = projectRepository.findProjectByIdAndUserId(projectId, user.getUserId());
        if (project == null) {
            throw new NotFoundException();
        }
        return projectMapper.toDto(project);
    }

    /**
     * Создается проект для пользователя. На данный момент пользователь может иметь только 3 проекта.
     * Ссылка проверяется в DTO. С помощью метода "isGitRepositoryExists" проверяется существование проекта и доступность
     * к нему.
     * @param data
     * @return
     */
    @Transactional()
    public ProjectDtoResponse createProject(CreateProjectDtoRequest data, ImpUserAuthDetails userAuthDetails) {

        if (projectRepository.findProjectsByUserId(userAuthDetails.getUserId()).size() >= 3) {
            throw new UserHasTooManyProjects();
        }

        byte projectExists = gitUtils.isGitRepositoryExists(data.link());
        if (projectExists != 0) {
            if (projectExists == 1) {
                throw new CantFindGitProjectException("Git project not found.");
            } else {
                throw new ServiceException("Can't find git project. Try later.");
            }
        }

        try {
            Project project = projectMapper.toEntity(data);
            project.setUser(userAuthDetails.getUser());
            projectRepository.save(project);
            return projectMapper.toDto(project);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public ProjectDtoResponse updateProject(ImpUserAuthDetails userAuthDetails, Long projectId, UpdateProjectDtoRequest data) {
        Project project = projectRepository.findProjectByIdAndUserId(projectId, userAuthDetails.getUserId());
        if (project == null) {
            throw new NotFoundException();
        }
        return projectMapper.toDto(projectMapper.updateProject(data, project));
    }

    public boolean deleteProjectById(Long projectId, Long userId) {
        Project project = projectRepository.findProjectByIdAndUserId(projectId, userId);
        if (project == null) {
            throw new AccessDeniedException("Access denied");
        }
        projectRepository.delete(project);
        return true;
    }

}
