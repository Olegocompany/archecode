package com.free.archecode.dto.user;

public class UserResponse {
    private Long id;
    private String name;
    private String surname;
    private String email;

    public UserResponse(Long id, String name, String surname, String email) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public Long getId() {
        return id;
    }
}
