package com.free.archecode.app.controller.user;

import com.free.archecode.app.role.RoleDto;
import com.free.archecode.app.user.User;
import com.free.archecode.app.user.UserMapper;
import com.free.archecode.app.user.UserRepository;
import com.free.archecode.app.user.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor // используя Lombok, самостоятельно сделает конструктор (отыграет autowired) и подгрузит зависимости в поля (dependency injection) (в данном случае репозиторий и сервис)
public class UserController {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

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


}
