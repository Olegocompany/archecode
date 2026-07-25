package com.free.archecode.utils;

import com.free.archecode.shared.config.security.user.ImpUserAuthDetails;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserUtils {

    /**
     * @return Возвращает текущего аутентифицированного пользователя (из токена), который обернут в класс для обработки информации о нем.
     * @throws AccessDeniedException, если пользователь не нашелся.
     */
    public ImpUserAuthDetails getUserAuth() throws AccessDeniedException {
        ImpUserAuthDetails user = (ImpUserAuthDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user == null) {
            throw new AccessDeniedException("User is not authenticated");
        }
        return  user;
    }

}
