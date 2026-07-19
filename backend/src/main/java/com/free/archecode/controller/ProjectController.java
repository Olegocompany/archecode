package com.free.archecode.controller;

import com.free.archecode.project.dto.response.ProjectDtoResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @GetMapping("/")
    public ResponseEntity<ProjectDtoResponse> index()
    {
        return ResponseEntity.ok();
    }

}
