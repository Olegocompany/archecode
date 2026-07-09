package com.free.archecode.app.user.service;

import com.free.archecode.app.user.User;
import com.free.archecode.app.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PasswordWorkService {
    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

//    public boolean checkPassword(User user, String password) {
//
//    }
}
