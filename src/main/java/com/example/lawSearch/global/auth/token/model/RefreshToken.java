package com.example.lawSearch.global.auth.token.model;

import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@Getter
@NoArgsConstructor
@RedisHash(value = "refreshToken")
public class RefreshToken {

    @Id
    private String refreshToken;

    private Long userId;

    private String role;

    @TimeToLive
    private long expiration;

    @Builder
    public RefreshToken(String refreshToken, Long userId, String role, long expiration) {
        this.refreshToken = refreshToken;
        this.userId = userId;
        this.role = role;
        this.expiration = expiration;
    }
}
