package com.free.archecode.shared.security.token.refreshToken;

import com.free.archecode.shared.config.security.user.UserAuthDetails;
import org.hibernate.service.spi.ServiceException;

public interface RefreshTokenService {
    public String generateToken(UserAuthDetails userDetails) throws ServiceException;
    public UserAuthDetails getUserByToken(String token);
}
