CREATE TABLE refresh_token
(
    id         SERIAL PRIMARY KEY,
    token_hash VARCHAR(255) NOT NULL UNIQUE,
    user_id    BIGINT unsigned NOT NULL,
    created_at timestamp default current_timestamp,
    expires_at datetime     NOT NULL,
    foreign key (user_id) references users(id) on DELETE CASCADE on update CASCADE,
    revoked boolean default false,

    index idx_refresh_token_user_id (user_id),
    index idx_refresh_token_and_revoked (token_hash, revoked)
) ENGINE=InnoDB, CHARSET=utf8mb4;
