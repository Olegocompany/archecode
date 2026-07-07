package com.free.archecode.repository.role;

import com.free.archecode.model.Role;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface RoleRepository extends Repository<Role, Long> {
    Role findByName(String name);
    Role save(Role role);
    Optional<Role> findById(Long id);
    Iterable<Role> findAll();
    void delete(Role Role);
    boolean existsById(Long id);
}
