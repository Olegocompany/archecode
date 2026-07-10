package com.free.archecode.app.user.dto;

import lombok.Data;

@Data
public class UpdateUserRequest {
    private String email;
    private String name;
    private String surname;
}
