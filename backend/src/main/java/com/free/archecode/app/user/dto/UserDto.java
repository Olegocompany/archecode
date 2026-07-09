package com.free.archecode.app.user.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.free.archecode.app.role.RoleDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

// закинуть поля в авто конструктор и создать геттеры для этих полей
@AllArgsConstructor
@Getter
public class UserDto {
    private Long id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String name;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String surname;
    private String email;
    @JsonProperty("role_name")
    private String role;
}
