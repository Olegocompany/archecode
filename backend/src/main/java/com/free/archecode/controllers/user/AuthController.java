package com.free.archecode.controllers.user;

import com.free.archecode.role.RoleRepository;
import com.free.archecode.user.UserMapper;
import com.free.archecode.user.UserRepository;
import com.free.archecode.user.dto.auth.LoginUserRequest;
import com.free.archecode.user.dto.auth.RegisterUserRequest;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private UserRepository userRepository;
    private UserMapper userMapper;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;


    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody RegisterUserRequest data) throws Exception {
        var user = userMapper.toEntity(data);
        if (data.getRole() == null) {
            throw new BadRequestException("invalid role");
        }
        user.setRole(roleRepository.findById(data.getRole()));
        user.setPassword(passwordEncoder.encode(data.getPassword()));
        userRepository.save(user);
        return ResponseEntity.ok(userMapper.toDto(user));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginUserRequest data) throws Exception {
        return ResponseEntity.ok("мне лень");
    }
}
