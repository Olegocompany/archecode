package com.free.archecode.user.service;

/*
Конкретно этот - будет вызываться DaoAuthProvider-ом для всяческих проверок и выгрузок пользователя
 */

import com.free.archecode.role.RoleRepository;
import com.free.archecode.user.User;
import com.free.archecode.user.UserDetails;
import com.free.archecode.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("can't find user with email: " + email));
        return new UserDetails(user);
    }

}
