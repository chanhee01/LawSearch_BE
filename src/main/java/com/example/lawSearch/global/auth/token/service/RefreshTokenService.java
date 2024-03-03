package com.example.lawSearch.global.auth.token.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.lawSearch.domain.user.exception.UserNotFoundException;
import com.example.lawSearch.domain.user.model.User;
import com.example.lawSearch.domain.user.repository.UserRepository;
import com.example.lawSearch.global.auth.jwt.JwtProperties;
import com.example.lawSearch.global.auth.token.exception.RefreshTokenExpiredException;
import com.example.lawSearch.global.auth.token.model.RefreshToken;
import com.example.lawSearch.global.auth.token.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;
    private final JwtProperties jwtProperties;

    public String createAccessToken(String refreshToken) {
        RefreshToken refreshTokenEntity = refreshTokenRepository.findById(refreshToken)
                .orElseThrow(() -> new RefreshTokenExpiredException("refresh 토큰이 만료되어 다시 로그인이 필요합니다."));

        // Refresh 토큰이 유효하면 새로운 Access 토큰 발급
        User user = userRepository.findById(refreshTokenEntity.getUserId())
                .orElseThrow(() -> new UserNotFoundException(refreshTokenEntity.getUserId()));
        String newAccessToken = JWT.create()
                .withSubject(user.getEmail())
                .withExpiresAt(new Date(System.currentTimeMillis() + jwtProperties.getExpirationTime()))
                .withClaim("id", refreshTokenEntity.getUserId())
                .withClaim("email", user.getEmail())
                .sign(Algorithm.HMAC512(jwtProperties.getSecret()));
        return newAccessToken;
    }
}
