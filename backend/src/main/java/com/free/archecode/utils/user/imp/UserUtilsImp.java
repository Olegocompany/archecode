package com.free.archecode.utils.user.imp;

import com.free.archecode.shared.config.security.user.UserAuthDetails;
import com.free.archecode.utils.user.UserAuthUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserUtilsImp implements UserAuthUtils {

    /**
     * @return Возвращает текущего аутентифицированного пользователя (из токена), который обернут в класс для обработки информации о нем.
     * @throws AccessDeniedException, если пользователь не нашелся.
     */
    public UserAuthDetails getUserAuth() throws AccessDeniedException {
        UserAuthDetails user = (UserAuthDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user == null) {
            throw new AccessDeniedException("User is not authenticated");
        }
        return  user;
    }

}
