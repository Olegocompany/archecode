package com.free.archecode.controller;

import com.free.archecode.project.dto.request.CreateProjectDtoRequest;
import com.free.archecode.project.dto.request.UpdateProjectDtoRequest;
import com.free.archecode.project.dto.response.ProjectDtoResponse;
import com.free.archecode.project.dto.response.ProjectsDetailsOfUserDtoResponse;
import com.free.archecode.project.service.ProjectService;
import com.free.archecode.utils.UserUtils;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;
    private final UserUtils  userUtils;

    public ProjectController(ProjectService projectService,  UserUtils userUtils) {
        this.projectService = projectService;
        this.userUtils = userUtils;
    }

    @GetMapping("/")
    public ResponseEntity<ProjectsDetailsOfUserDtoResponse> index()
    {
        return ResponseEntity.ok(projectService.getProjectsOfUser(
                userUtils.getUserAuth()
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDtoResponse> show(@PathVariable Long id) {
        return ResponseEntity.ok(projectService.getProjectByIdOfUserById(
                userUtils.getUserAuth(),
                id
        ));
    }

    @PostMapping("/")
    public ResponseEntity<ProjectDtoResponse> create(@RequestBody @Valid CreateProjectDtoRequest request)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(projectService.createProject(request, userUtils.getUserAuth()));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProjectDtoResponse> update(@PathVariable Long id, @RequestBody @Valid UpdateProjectDtoRequest request)
    {
        return ResponseEntity.ok(projectService.updateProject(
                userUtils.getUserAuth(),
                id,
                request
        ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?>  delete(@PathVariable Long id)
    {
        projectService.deleteProjectById(id, userUtils.getUserAuth().getUserId());
        return ResponseEntity.noContent().build();
    }



}
