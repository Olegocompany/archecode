package com.free.archecode.app.controller.user;

import com.free.archecode.app.role.RoleDto;
import com.free.archecode.app.role.RoleRepository;
import com.free.archecode.app.user.User;
import com.free.archecode.app.user.UserMapper;
import com.free.archecode.app.user.UserRepository;
import com.free.archecode.app.user.dto.RegisterUserRequest;
import com.free.archecode.app.user.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/users")
@AllArgsConstructor // используя Lombok, самостоятельно сделает конструктор (отыграет autowired) и подгрузит зависимости в поля (dependency injection) (в данном случае репозиторий и сервис)
public class UserController {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;

    @GetMapping("")
    public List<UserDto> getUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toDto) // user -> userMapper.toDto(user)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id) {
        var user = userRepository.findById(id).orElse(null);
        if (user == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(
                userMapper.toDto(user)
        );
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> createUser(@RequestBody RegisterUserRequest data) {
        var user = userMapper.toEntity(data);
//        return ResponseEntity.ok(data);
        user.setRole(roleRepository.findById(data.getRole()).orElse(null));
        user.setPassword(new BCryptPasswordEncoder().encode(data.getPassword()));
        userRepository.save(user);
        return ResponseEntity.ok(userMapper.toDto(user));
    }

}
