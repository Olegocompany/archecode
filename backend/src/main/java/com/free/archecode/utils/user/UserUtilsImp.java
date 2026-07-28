package com.free.archecode.utils.user;

import com.free.archecode.shared.config.security.user.UserAuthDetailsImp;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserUtilsImp implements UserAuthUtils {

    /**
     * @return Возвращает текущего аутентифицированного пользователя (из токена), который обернут в класс для обработки информации о нем.
     * @throws AccessDeniedException, если пользователь не нашелся.
     */
    public UserAuthDetailsImp getUserAuth() throws AccessDeniedException {
        UserAuthDetailsImp user = (UserAuthDetailsImp) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user == null) {
            throw new AccessDeniedException("User is not authenticated");
        }
        return  user;
    }

}
