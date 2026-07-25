package com.free.archecode.project.service;

import com.free.archecode.project.Project;
import com.free.archecode.project.ProjectRepository;
import com.free.archecode.project.dto.ProjectDto;
import com.free.archecode.project.dto.ProjectMapper;
import com.free.archecode.project.dto.response.ProjectsOfUserDtoResponse;
import com.free.archecode.shared.common.exceptions.project.CantFindGitProjectException;import com.free.archecode.shared.common.exceptions.project.UserHasTooManyProjects;import com.free.archecode.shared.config.security.user.ImpUserAuthDetails;
import com.free.archecode.user.User;
import com.free.archecode.user.UserRepository;import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

@Service
@Transactional
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;private final UserRepository userRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository, ProjectMapper projectMapper, UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.projectMapper = projectMapper;this.userRepository = userRepository;}

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

    /**
     * Создается проект для пользователя. На данный момент пользователь может иметь только 3 проекта.
     * Ссылка проверяется в DTO. С помощью метода "isGitRepositoryExists" проверяется существование проекта и доступность
     * к нему.
     * @param data
     * @return
     */
    @Transactional()
    public ProjectDto createProject(ProjectDto data) {
        User user;
        try {
            user = ((ImpUserAuthDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
        } catch (NullPointerException e) {
            throw new UsernameNotFoundException(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        if (projectRepository.findProjectsByUserId(user.getId()).size() >= 3) {
            throw new UserHasTooManyProjects();
        }

        byte projectExists = isGitRepositoryExists(data.link());
        if (projectExists != 0) {
            if (projectExists == 1) {
                throw new CantFindGitProjectException("Git project not found.");
            } else {
                throw new ServiceException("Can't find git project. Try later.");
            }
        }

        try {
            Project project = projectMapper.toEntity(data);
            project.setUser(user);
            projectRepository.save(project);
            return projectMapper.toDto(project);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    /**
     * Проверяет на существование Git-репозитория. Использует Git команду
     * "git ls-remote --exit-code -h".
     * Используется HTTPS ссылка.
     * @param link
     * @return 0 - found, 1 - not found, 2 - error (maybe in system)
     */
    private byte isGitRepositoryExists(String link)
    {
        if (!link.startsWith("https://")) {
            link = "https://" + link;
        }

        ProcessBuilder pb = new ProcessBuilder("git", "ls-remote", "--exit-code", "-h", link);
        pb.redirectErrorStream(true);
        pb.environment().put("GIT_TERMINAL_PROMPT", "0");

        try {
            Process p = pb.start();
            p.waitFor(10, TimeUnit.SECONDS);

            if (p.exitValue() == 0) {
                return 0;
            } else {
                return 1;
            }
        } catch (Exception e) {
            return 2;
        }
    }

}
