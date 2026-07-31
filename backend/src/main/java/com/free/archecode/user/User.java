package com.free.archecode.user;

import com.free.archecode.project.Project;
import com.free.archecode.role.Role;

import jakarta.persistence.*;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "users",
        uniqueConstraints = @UniqueConstraint(columnNames = {"email"})
)
@ToString(exclude = {"role", "projects"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Identity указываем, чтобы работало как автоинкремент
    private Long id;

    private String email;
    private String username;
    private String password;

    private String name;
    private String surname;

    private String imageLink;


    @ManyToOne(optional = false)
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Project> projects;
    
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

    public void setUsername(String username) {
        this.username = username;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setImageLink(String imageLink) { this.imageLink = imageLink; }

    // getters
    public String getEmail() {
        return email;
    }

    public String getUsername() { return username; }

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

    public List<Project> getProjects() {
        return projects;
    }

    public String getImageLink() { return imageLink; }

}
