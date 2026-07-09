package com.free.archecode.app.user.service;

import com.free.archecode.app.role.Role;
import com.free.archecode.app.role.RoleRepository;
import com.free.archecode.app.user.User;
import com.free.archecode.app.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    // spring должен сам подставить сюда
    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public User createUserWithWorkerRole(String name, String surname, String email, String password) {
        Role workerRole = roleRepository.findByName("worker");
        if (workerRole == null) {
            throw new RuntimeException("No worker role found");
        }

        User user = new User();
        user.setName(name);
        user.setSurname(surname);
        user.setEmail(email);
        user.setPassword(new BCryptPasswordEncoder().encode(password));
        user.setRole(workerRole);

        return userRepository.save(user);
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
