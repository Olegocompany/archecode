package com.free.archecode.shared.config.security.user;

import com.free.archecode.shared.config.security.user.imps.UserAuthDetailsImp;
import com.free.archecode.user.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserAuthDetailsService {
    public UserAuthDetailsImp loadUserByUsername(String username) throws UsernameNotFoundException;
    public UserAuthDetailsImp loadUserByUserId(Long id) throws UsernameNotFoundException;
    public UserAuthDetailsImp loadUser(User user) throws Exception;
}
