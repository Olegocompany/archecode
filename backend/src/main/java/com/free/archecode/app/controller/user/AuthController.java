package com.free.archecode.app.controller.user;

import com.free.archecode.app.role.RoleRepository;
import com.free.archecode.app.user.User;
import com.free.archecode.app.user.UserMapper;
import com.free.archecode.app.user.UserRepository;
import com.free.archecode.app.user.dto.RegisterUserRequest;
import com.free.archecode.app.user.dto.UserDto;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
