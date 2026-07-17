package com.free.archecode.shared.security.token.refreshToken;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByTokenHash(String tokenHash);

    @Modifying
    @NativeQuery("DELETE FROM refresh_token WHERE expires_at < NOW()")
    void deleteExpired();

    @Modifying
    @Query("UPDATE RefreshToken rt SET rt.revoked = true WHERE rt.userId = :userId")
    void revokeAllTokensByUserId(@Param("userId") Long userId);

    @Modifying
    @Query("UPDATE RefreshToken rt SET rt.revoked = true WHERE rt.tokenHash = ?1")
    void revokeTokenByTokenHash(String tokenHash);

    @Query("SELECT rt from RefreshToken where rt.revoked = false and rt.userId = ?1")
    void findAllByUserIdWhereNotRevoked(Long userId);
}
