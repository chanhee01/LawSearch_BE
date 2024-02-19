package com.example.lawSearch.global.auth.token.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import java.util.List;

@Getter
@NoArgsConstructor
@RedisHash(value = "refreshToken")
public class RefreshToken {

    @Id
    private String refreshToken;

    private Long userId;

    private List<String> roles;

    @TimeToLive
    private long expiration;

    @Builder
    public RefreshToken(String refreshToken, Long userId, List<String> roles, long expiration) {
        this.refreshToken = refreshToken;
        this.userId = userId;
        this.roles = roles;
        this.expiration = expiration;
    }
}
