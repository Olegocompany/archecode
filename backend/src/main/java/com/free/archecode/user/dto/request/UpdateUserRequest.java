package com.free.archecode.user.dto.request;

import lombok.Data;

@Data
public class UpdateUserRequest {
    private String email;
    private String name;
    private String surname;
}
