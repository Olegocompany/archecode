package com.free.archecode.controllers.user;

import com.free.archecode.role.RoleRepository;
import com.free.archecode.user.UserMapper;
import com.free.archecode.user.UserRepository;
import com.free.archecode.user.dto.UpdateUserRequest;
import com.free.archecode.user.dto.UserDto;
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
    private final RoleRepository roleRepository;

//    @GetMapping("")
//    public List<UserDto> getUsers() {
//        return userRepository.findAll().stream()
//                .map(userMapper::toDto) // user -> userMapper.toDto(user)
//                .toList();
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<UserDto> getUser(@PathVariable Long id) {
//        var user = userRepository.findById(id).orElse(null);
//        if (user == null){
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(
//                userMapper.toDto(user)
//        );
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UpdateUserRequest data) {
//        var user = userRepository.findById(id).orElse(null);
//        if (user == null){
//            ResponseEntity.notFound().build();
//        }
//
//        userMapper.update(data, user);
//        userRepository.save(user);
//
//        return ResponseEntity.ok(userMapper.toDto(user));
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<UserDto> deleteUser(@PathVariable Long id) {
//        var user = userRepository.findById(id).orElse(null);
//        if (user == null){
//            ResponseEntity.notFound().build();
//        }
//
//        userRepository.delete(user);
//        return ResponseEntity.noContent().build();
//    }


}
