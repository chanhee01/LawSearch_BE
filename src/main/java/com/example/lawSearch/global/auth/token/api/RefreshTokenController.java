package com.example.lawSearch.global.auth.token.api;

import com.example.lawSearch.global.auth.jwt.JwtProperties;
import com.example.lawSearch.global.auth.token.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/token")
public class RefreshTokenController {

    private final RefreshTokenService refreshTokenService;
    private final JwtProperties jwtProperties;

    @PostMapping
    public ResponseEntity<?> refreshAccessToken(@RequestHeader("Refresh-Token") String refreshToken) {
        String newAccessToken = refreshTokenService.createAccessToken(refreshToken);

        return ResponseEntity.ok().header(jwtProperties.getHeaderString(), jwtProperties.getTokenPrefix() + newAccessToken).build();
    }
}
