package com.free.archecode.shared.security.token.refreshToken;

import com.free.archecode.shared.common.exceptions.refreshToken.TokenExpiredException;
import com.free.archecode.shared.common.exceptions.refreshToken.TokenRevokedException;
import com.free.archecode.shared.config.security.user.ImpUserAuthDetails;
import com.free.archecode.shared.config.security.user.ImpUserAuthDetailsService;import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.service.spi.ServiceException;import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

/*
конкретно тут реализация сильно завязана на User
 */

@Service
@Transactional
public class RefreshTokenService {

    private final RefreshTokenRepository tokenRepository;
    private final ImpUserAuthDetailsService impUserAuthDetailsService;

    @Value("${jwt.refresh.expiration}")
    private long expiration;

    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository,  ImpUserAuthDetailsService impUserAuthDetailsService) {
        this.tokenRepository = refreshTokenRepository;
        this.impUserAuthDetailsService = impUserAuthDetailsService;
    }

    /**
     * Генерирует токен для пользователя и сохраняет запись в бд.
     * @throws ServiceException
     * @param userDetails
     * @return
     */
    @Transactional
    public String generateToken(ImpUserAuthDetails userDetails) throws ServiceException {
        Long userId = userDetails.getUserId();
        String token = UUID.randomUUID().toString();
        String sha256hex = DigestUtils.sha256Hex(token);

        RefreshToken rt = new RefreshToken(sha256hex, userId, new Date(System.currentTimeMillis() + expiration));
        try {
            tokenRepository.save(rt);
        } catch (Exception e) {
            // если предполагать, что выпало UniqueConstraint
            try {
                token = UUID.randomUUID().toString();
                sha256hex = DigestUtils.sha256Hex(token);
                rt.setTokenHash(sha256hex);
                tokenRepository.save(rt);
            } catch (Exception e2) {
                throw new ServiceException("Could not save refresh token", e2);
            }
        }
        return token;
    }

    /**
     * Проверяет на наличие токена в бд (хеш), проверяет дополнительно и возвращает пользователя, если такой найден.
     * После нахождения отмечает токен как использованный.
     * Использованный токен не удаляется, а просто отзывается.
     * @param token
     * @return ImpUserAuthDetails | null (if not valid)
     */
    @Transactional
    @Nullable
    public ImpUserAuthDetails getUserByToken(String token) {
        String tokenHash = DigestUtils.sha256Hex(token);
        Optional<RefreshToken> tokenObj = tokenRepository.findByTokenHash(tokenHash);
        if (!tokenObj.isPresent()) {
            return null;
        }

        RefreshToken rt = tokenObj.get();

        if (rt.isRevoked()) {
            // токеном воспользовались еще раз, что странно.
            throw new TokenRevokedException("Potential token compromise detected!");
        }
        if (new Date().after(rt.getExpiresAt())) {
            throw new TokenExpiredException("Token expired.");
        }

        try {
            ImpUserAuthDetails user = impUserAuthDetailsService.loadUserByUserId(rt.getUserId());
            return user;
        } catch (UsernameNotFoundException e) {
            return null;
        }

    }

}
