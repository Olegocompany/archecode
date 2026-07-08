package com.free.archecode.app.user;

import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface UserRepository extends Repository<User, Long> {
    User findByName(String name);
    User findBySurname(String surname);
    User findByEmail(String email);
    User save(User user);
    Optional<User> findById(Long id);
    Iterable<User> findAll();
    void delete(User user);
    long count();
    boolean existsById(Long id);
}
