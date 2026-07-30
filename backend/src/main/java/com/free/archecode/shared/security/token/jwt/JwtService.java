package com.free.archecode.shared.security.token.jwt;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    /**
     *
     * @param userDetails
     * @return JWT-токен для пользователя.
     */
    public String generateToken(UserDetails userDetails);

    /**
     * Валидирует токен и возвращает true, если токен соответствует подписи и не просрочен
     * @param token
     * @param userDetails
     * @return true if it's okay.
     */
    public boolean validateToken(String token, UserDetails userDetails);

    public String extractUsername(String token);
}
