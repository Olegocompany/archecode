package com.free.archecode.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "roles", uniqueConstraints = {@UniqueConstraint(columnNames = "name")})
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "role") // это поле, которое в модели, а не в самой бд
    private Set<User> users;
}
