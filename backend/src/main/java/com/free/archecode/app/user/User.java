package com.free.archecode.app.user;

import com.free.archecode.app.role.Role;
import com.free.archecode.app.role.RoleRepository;
import jakarta.persistence.*;
import lombok.ToString;

@Entity
@Table(name = "users")
@ToString(exclude = "role")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Identity указываем, чтобы работало как автоинкремент
    private Long id;

    private String email;
    private String password;

    private String name;
    private String surname;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    // setters
    public void setRole(Role role) {
        this.role = role;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // getters
    public String getEmail() {
        return email;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getRole() {
        return role.getName();
    }

    public String getPassword() {
        return password;
    }

}
