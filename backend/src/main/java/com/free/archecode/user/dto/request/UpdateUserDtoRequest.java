package com.free.archecode.user.dto.request;

import lombok.Data;

@Data
public class UpdateUserDtoRequest {
    private String email;
    private String name;
    private String surname;
}
