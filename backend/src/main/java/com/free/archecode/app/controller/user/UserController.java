package com.free.archecode.app.controller.user;

import com.free.archecode.app.user.dto.UserRegistrationRequest;
import com.free.archecode.app.user.dto.UserResponse;
import com.free.archecode.app.user.User;
import com.free.archecode.app.user.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public UserResponse registerUser(@RequestBody UserRegistrationRequest request) {
        System.out.println("registerUser");
        User user = userService.createUserWithWorkerRole(
                request.getName(),
                request.getSurname(),
                request.getEmail(),
                request.getPassword()
        );

        return new UserResponse(user.getId(), user.getName(), user.getSurname(), user.getEmail());
    }
}
