package com.free.archecode.shared.security.token.refreshToken;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByTokenHash(String tokenHash);

    @Modifying
    @NativeQuery("DELETE FROM refresh_token WHERE expires_at < NOW()")
    void deleteExpired();

    @Modifying
    @Query("UPDATE RefreshToken rt SET rt.revoked = true WHERE rt.userId = :user_id")
    void revokeAllTokensByUserId(@Param("g_userId") Long userId);

    @Modifying
    @Query("UPDATE RefreshToken rt SET rt.revoked = true WHERE rt.tokenHash = :g_tokenHash")
    void revokeTokenByTokenHash(@Param("g_tokenHash") String tokenHash);

    List<RefreshToken> findAllByUserIdAndRevokedFalse(Long userId);
}
