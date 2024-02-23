package com.example.lawSearch.global.auth.jwt;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class JwtProperties {

    @Value("${jwt.secret}")
    private String secret;

    private Integer expirationTime = 1200000; // 20분

    private Integer refreshTokenTime = 604800000; // 일주일

    private String tokenPrefix = "Bearer ";

    private String headerString = "Authorization";
}