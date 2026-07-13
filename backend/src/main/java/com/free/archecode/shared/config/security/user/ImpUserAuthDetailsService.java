package com.free.archecode.shared.config.security.user;

/*
Конкретно этот - будет вызываться DaoAuthProvider-ом для всяческих проверок и выгрузок пользователя
 */

import com.free.archecode.user.User;
import com.free.archecode.user.UserRepository;
import lombok.AllArgsConstructor;
import org.jspecify.annotations.NullMarked;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@SuppressWarnings("RedundantThrows")
@Service
@AllArgsConstructor
public class ImpUserAuthDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Override
    @NullMarked // явное подтверждение контракта, что тут вообще ничего не может быть Null
    public ImpUserAuthDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return new ImpUserAuthDetails(
                userRepository.findByEmail(email)
                        .orElseThrow(() -> new UsernameNotFoundException("can't find user with email: " + email))
        );
    }

    @NullMarked
    public ImpUserAuthDetails loadUserByUserId(Long id) throws UsernameNotFoundException {
        return new ImpUserAuthDetails(
                userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("can't find user with id: " + id))
        );
    }

    @NullMarked
    public ImpUserAuthDetails loadUser(User user) throws Exception {
        return new ImpUserAuthDetails(user);
    }

}
