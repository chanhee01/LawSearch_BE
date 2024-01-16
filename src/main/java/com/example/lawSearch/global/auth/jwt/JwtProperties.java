package com.example.lawSearch.global.auth.jwt;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class JwtProperties {

    @Value("${jwt.secret}")
    private String secret;

    private Integer expirationTime = 86400000;

    private String tokenPrefix = "Bearer ";

    private String headerString = "Authorization";
}