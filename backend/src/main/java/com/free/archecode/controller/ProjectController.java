package com.free.archecode.controller;

import com.free.archecode.project.dto.response.forUser.ProjectsOfUserDtoResponse;
import com.free.archecode.project.service.ProjectService;
import com.free.archecode.shared.config.security.user.ImpUserAuthDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/")
    public ResponseEntity<ProjectsOfUserDtoResponse> index()
    {
        return ResponseEntity.ok(projectService.getProjectsOfUser(
                (ImpUserAuthDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()
            )
        );
    }

}
