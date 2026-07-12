package com.free.archecode.controller.user;

import com.free.archecode.role.RoleRepository;
import com.free.archecode.user.security.ImpUserAuthDetails;import com.free.archecode.user.User;
import com.free.archecode.user.dto.UserMapper;
import com.free.archecode.user.UserRepository;
import com.free.archecode.user.dto.auth.LoginUserRequest;
import com.free.archecode.user.dto.auth.RegisterUserRequest;
import com.free.archecode.shared.security.jwt.JwtService;import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private UserRepository userRepository;
    private UserMapper userMapper;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private JwtService jwtService;


    @PostMapping("/register")
    public ResponseEntity<?> createUser(
            @RequestBody @Valid RegisterUserRequest data
    ) {
        System.out.println("createUser");
        String roleName = data.getRole_name();
        if (!(Set.of("user", "worker").contains(roleName))) {
            return ResponseEntity.badRequest().body(Map.of("message", "available roles: worker, manager"));
        }

        var user = userMapper.toEntity(data);
        user.setRole(roleRepository.findByName(roleName));
        user.setPassword(passwordEncoder.encode(data.getPassword()));
        userRepository.save(user);
        return ResponseEntity.ok(userMapper.toAuthResponse(generateToken(user)));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginUserRequest data) {
        User user = userRepository.findByEmail(data.getEmail()).orElse(null);
        if (user == null) {
            return ResponseEntity.status(403).build();
        }

        if (!passwordEncoder.matches(data.getPassword(), user.getPassword())) {
            return ResponseEntity.status(403).build();
        }
        return ResponseEntity.ok(userMapper.toAuthResponse(generateToken(user)));
    }

    private String generateToken(User user) {
        return jwtService.generateToken(new ImpUserAuthDetails(user));
    }
}
