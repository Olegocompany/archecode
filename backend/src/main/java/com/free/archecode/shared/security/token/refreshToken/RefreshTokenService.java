package com.free.archecode.shared.security.token.refreshToken;

import com.free.archecode.shared.config.security.user.imps.UserAuthDetailsImp;
import org.hibernate.service.spi.ServiceException;

public interface RefreshTokenService {
    public String generateToken(UserAuthDetailsImp userDetails) throws ServiceException;
    public UserAuthDetailsImp getUserByToken(String token);
}
