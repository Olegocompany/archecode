package com.free.archecode.project;

import com.free.archecode.user.User;
import jakarta.persistence.*;

@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;
    private String description;

    @Column(nullable = false)
    private String link;

    private String branch;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    public Project() {}

    public Project(String name, String description, String link, String branch, User user) {
        this.name = name;
        this.description = description;
        this.link = link;
        this.branch = branch;
        this.user = user;
    }

    // getters and setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
