package com.free.archecode.app.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByName(String name);
    User findBySurname(String surname);
    Optional<User> findByEmail(String email);
}
