package com.free.archecode.project.dto.response;

public record ProjectDtoResponse (
        Long id,
        String name,
        String description,
        String link,
        String branch
){}
