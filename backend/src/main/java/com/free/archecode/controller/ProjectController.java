package com.free.archecode.controller;

import com.free.archecode.project.dto.ProjectDto;
import com.free.archecode.project.dto.response.ProjectsOfUserDtoResponse;
import com.free.archecode.project.service.ProjectService;
import com.free.archecode.shared.config.security.user.ImpUserAuthDetails;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

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
        try {
            return ResponseEntity.ok(projectService.getProjectsOfUser(
                    (ImpUserAuthDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()
                )
            );}
        catch (NullPointerException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/")
    public ResponseEntity<ProjectDto> createProject(@RequestBody @Valid ProjectDto request)
    {
        return ResponseEntity.ok(projectService.createProject(request));
    }

}
