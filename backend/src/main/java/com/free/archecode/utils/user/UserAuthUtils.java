package com.free.archecode.utils.user;

import com.free.archecode.shared.config.security.user.UserAuthDetailsImp;
import org.springframework.security.access.AccessDeniedException;

public interface UserAuthUtils {
    public UserAuthDetailsImp getUserAuth() throws AccessDeniedException;
}
