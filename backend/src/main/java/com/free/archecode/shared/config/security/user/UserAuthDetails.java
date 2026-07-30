package com.free.archecode.shared.config.security.user;

import com.free.archecode.user.User;

public interface UserAuthDetails {
    public Long getUserId();
    public User getUser();
}
