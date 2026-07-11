package com.free.archecode.user.service;

import com.free.archecode.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class PasswordWorkService {
    private UserRepository userRepository;

//    public boolean checkPassword(User user, String password) {
//
//    }
}
