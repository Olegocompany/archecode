package com.free.archecode.user.service;

/*
Конкретно этот - будет вызываться DaoAuthProvider-ом для всяческих проверок и выгрузок пользователя
 */

import com.free.archecode.user.ImpUserDetails;
import com.free.archecode.user.UserRepository;
import lombok.AllArgsConstructor;
import org.jspecify.annotations.NullMarked;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetailsService;

@Service
@AllArgsConstructor
public class ImpUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Override
    @NullMarked // явное подтверждение контракта, что тут вообще ничего не может быть Null
    public ImpUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return new ImpUserDetails(
                userRepository.findByEmail(email)
                        .orElseThrow(() -> new UsernameNotFoundException("can't find user with email: " + email))
        );
    }

}
