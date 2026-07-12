package com.free.archecode.user.service;

import com.free.archecode.role.RoleRepository;
import com.free.archecode.shared.config.security.jwt.JwtService;
import com.free.archecode.shared.config.security.user.ImpUserAuthDetails;
import com.free.archecode.shared.exceptions.InvalidRoleException;
import com.free.archecode.user.User;
import com.free.archecode.user.UserRepository;
import com.free.archecode.user.dto.UserMapper;
import com.free.archecode.user.dto.auth.AuthResponse;
import com.free.archecode.user.dto.auth.LoginUserRequest;
import com.free.archecode.user.dto.auth.RegisterUserRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Transactional
@AllArgsConstructor
public class AuthService {

    private UserRepository userRepository;
    private UserMapper userMapper;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private JwtService jwtService;

    private static final Set<String> ALLOWED_ROLES = Set.of("user", "worker");

    public AuthResponse register(RegisterUserRequest data) {
        validateRole(data.getRole_name());

        User user = userMapper.toEntity(data);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(roleRepository.findByName(data.getRole_name()));
        userRepository.save(user);
        return userMapper.toAuthResponse(generateToken(user));
    }

    public AuthResponse login(LoginUserRequest data) {
        User user = userRepository.findByEmail(data.getEmail())
                .orElseThrow(() -> new BadCredentialsException("Invalid credentials"));

        if (!passwordEncoder.matches(data.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Invalid credentials");
        }

        return userMapper.toAuthResponse(generateToken(user));
    }


    private void validateRole(String roleName) {
        if (!ALLOWED_ROLES.contains(roleName)) {
            throw new InvalidRoleException("Available roles: " + String.join(", ", ALLOWED_ROLES));
        }
    }

    private String generateToken(User user) {
        return jwtService.generateToken(new ImpUserAuthDetails(user));
    }
}
