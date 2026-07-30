package com.free.archecode.shared.config.security.user.imps;

/*
Конкретно этот - будет вызываться DaoAuthProvider-ом для всяческих проверок и выгрузок пользователя
 */

import com.free.archecode.shared.config.security.user.UserAuthDetailsService;
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
public class UserAuthDetailsServiceImp implements UserDetailsService, UserAuthDetailsService {

    private UserRepository userRepository;

    @Override
    @NullMarked // явное подтверждение контракта, что тут вообще ничего не может быть Null
    public UserAuthDetailsImp loadUserByUsername(String username) throws UsernameNotFoundException {
        return new UserAuthDetailsImp(
                userRepository.findByUsername(username)
                        .orElseThrow(() -> new UsernameNotFoundException("can't find user with username: " + username))
        );
    }

    @NullMarked
    public UserAuthDetailsImp loadUserByUserId(Long id) throws UsernameNotFoundException {
        return new UserAuthDetailsImp(
                userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("can't find user with id: " + id))
        );
    }

    @NullMarked
    public UserAuthDetailsImp loadUser(User user) throws Exception {
        return new UserAuthDetailsImp(user);
    }

}
