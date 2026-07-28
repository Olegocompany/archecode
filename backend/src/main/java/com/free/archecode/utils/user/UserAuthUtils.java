package com.free.archecode.utils.user;

import com.free.archecode.shared.config.security.user.UserAuthDetails;
import org.springframework.security.access.AccessDeniedException;

public interface UserAuthUtils {
    public UserAuthDetails getUserAuth() throws AccessDeniedException;
}
