package com.free.archecode.controller;

import com.free.archecode.project.dto.request.CreateProjectDtoRequest;
import com.free.archecode.project.dto.request.UpdateProjectDtoRequest;
import com.free.archecode.project.dto.response.ProjectDtoResponse;
import com.free.archecode.project.dto.response.ProjectsDetailsOfUserDtoResponse;
import com.free.archecode.project.service.ProjectService;
import com.free.archecode.shared.config.security.user.ImpUserAuthDetails;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<ProjectsDetailsOfUserDtoResponse> index()
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

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDtoResponse> show(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(projectService.getProjectByIdOfUserById(
                    (ImpUserAuthDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal(),
                    id
            ));
        } catch (NullPointerException e) {
            return ResponseEntity.status(403).build();
        }
    }

    @PostMapping("/")
    public ResponseEntity<ProjectDtoResponse> createProject(@RequestBody @Valid CreateProjectDtoRequest request)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(projectService.createProject(request));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProjectDtoResponse> updateProject(@PathVariable Long id, @RequestBody @Valid UpdateProjectDtoRequest request)
    {
        try {
            return ResponseEntity.ok(projectService.updateProject(
                    (ImpUserAuthDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal(),
                    id,
                    request
            ));
        } catch (NullPointerException e) {
            return ResponseEntity.status(403).build();
        }
    }

}
