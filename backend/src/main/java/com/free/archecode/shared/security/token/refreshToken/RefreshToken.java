package com.free.archecode.shared.security.token.refreshToken;

import jakarta.persistence.*;import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "refresh_token")
@Data
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="token_hash", nullable=false)
    private String tokenHash;

    @Column(name="user_id",  nullable=false)
    private Long userId;

    @Column(nullable = false)
    private boolean revoked;

    @Column(nullable = false, name="created_at")
    private Date createdAt;

    @Column(nullable = false, name = "expires_at")
    private Date expiresAt;

    public RefreshToken() {}

    public RefreshToken(
            String tokenHash, Long userId, Date expiresAt
    ) {
        this.tokenHash = tokenHash;
        this.userId = userId;
        this.expiresAt = expiresAt;
    }
}