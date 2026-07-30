package com.free.archecode.user.service.imp;

import com.free.archecode.role.RoleRepository;
import com.free.archecode.shared.common.exceptions.InvalidRoleException;
import com.free.archecode.shared.config.security.user.imps.UserAuthDetailsImp;
import com.free.archecode.shared.security.token.jwt.JwtService;
import com.free.archecode.shared.security.token.refreshToken.RefreshTokenService;
import com.free.archecode.user.User;
import com.free.archecode.user.UserRepository;
import com.free.archecode.user.dto.UserMapper;
import com.free.archecode.user.dto.auth.request.LoginUserDtoRequest;
import com.free.archecode.user.dto.auth.request.RegisterUserDtoRequest;
import com.free.archecode.user.dto.auth.response.ContainerAuthDtoResponse;
import com.free.archecode.user.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.regex.Pattern;

@Service
@Transactional
@AllArgsConstructor
public class AuthServiceImp implements AuthService {

    private UserRepository userRepository;
    private UserMapper userMapper;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private JwtService jwtService;
    private RefreshTokenService refreshTokenService;

    private static final Set<String> ALLOWED_ROLES = Set.of("user", "worker");

    public ContainerAuthDtoResponse register(RegisterUserDtoRequest data) {
        validateRole(data.getRole_name());

        User user = userMapper.toEntity(data);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(roleRepository.findByName(data.getRole_name()));
        userRepository.save(user);
        return generateTokenPair(new UserAuthDetailsImp(user));
    }

    /**
     * @throws BadCredentialsException
     * @param data (from request)
     * @return pair jwtToken + refreshToken
     */
    public ContainerAuthDtoResponse login(LoginUserDtoRequest data) {
        User user;
        if (Pattern.matches("^[а-яА-ЯеЁa-zA-Z0-9!#$%&'\"*-=+-?^_{|}~.]+@+([а-яА-Яa-zA-ZеЁ]*).([a-zA-Zа-яА-ЯёЁ]){1,10}$", data.credential())) {
            // если это почта
            user = userRepository.findByEmail(data.credential())
                    .orElseThrow(() -> new BadCredentialsException("Invalid credentials"));
        } else {
            user = userRepository.findByUsername(data.credential())
                    .orElseThrow(() -> new BadCredentialsException("Invalid credentials"));
        }

        if (!passwordEncoder.matches(data.password(), user.getPassword())) {
            throw new BadCredentialsException("Invalid credentials");
        }

        return generateTokenPair(new UserAuthDetailsImp(user));
    }

    /**
     * @throws BadCredentialsException
     * @param refreshToken
     * @return pair jwtToken + refreshToken
     */
    public ContainerAuthDtoResponse refreshToken(String refreshToken) {
        UserAuthDetailsImp user = refreshTokenService.getUserByToken(refreshToken);
        if (user == null) {
            throw new BadCredentialsException("Invalid token");
        }
        return generateTokenPair(user);
    }

    /**
     * @throws InvalidRoleException
     * @param roleName
     */
    private void validateRole(String roleName) {
        if (!ALLOWED_ROLES.contains(roleName)) {
            throw new InvalidRoleException("Available roles: " + String.join(", ", ALLOWED_ROLES));
        }
    }

    /**
     * @param user
     * @return ContainerAuthResponse (jwtToken, refreshToken)
     */
    private ContainerAuthDtoResponse generateTokenPair(UserAuthDetailsImp user) {
        String jwtToken = jwtService.generateToken(user);
        String refreshToken = refreshTokenService.generateToken(user);

        return userMapper.toAuthResponse(jwtToken, refreshToken);
    }

}
