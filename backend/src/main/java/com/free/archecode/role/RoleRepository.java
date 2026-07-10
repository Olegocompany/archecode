package com.free.archecode.role;

import org.springframework.data.repository.Repository;

public interface RoleRepository extends Repository<Role, Long> {

    Role findByName(String name);

    Role save(Role role);

    Role findById(Long id);

    Iterable<Role> findAll();

    void delete(Role Role);

    boolean existsById(Long id);

}
