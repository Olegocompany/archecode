package com.free.archecode.shared.config.security.user;

import com.free.archecode.user.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserAuthDetails extends UserDetails {
    public Long getUserId();
    public User getUser();
}
