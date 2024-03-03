package com.example.lawSearch.global.auth.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.lawSearch.domain.user.dto.request.UserRequestDto;
import com.example.lawSearch.global.auth.PrincipalDetails;
import com.example.lawSearch.global.auth.token.model.RefreshToken;
import com.example.lawSearch.global.auth.token.repository.RefreshTokenRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JwtProperties jwtProperties;
    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        ObjectMapper om = new ObjectMapper();
        UserRequestDto loginRequestDto = null;
        try {
            loginRequestDto = om.readValue(request.getInputStream(), UserRequestDto.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginRequestDto.getEmail(), loginRequestDto.getPassword());
        // user의 정보를 가지고 토큰을 만듦

        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        // authenticationManager를 만들어서 위에서 생성한 토큰을 넣으면서 PrincipalDetailsService의 loadUserByUsername() 함수가 실행

        return authentication;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        PrincipalDetails principalDetails = (PrincipalDetails) authResult.getPrincipal();

        String jwtToken = JWT.create()
                .withSubject(principalDetails.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + jwtProperties.getExpirationTime()))
                .withClaim("id", principalDetails.getUser().getId())
                .withClaim("email", principalDetails.getUser().getEmail())
                .sign(Algorithm.HMAC512(jwtProperties.getSecret()));

        String refreshToken = UUID.randomUUID().toString();
        RefreshToken refreshTokenEntity = RefreshToken.builder()
                .refreshToken(refreshToken)
                .userId(principalDetails.getUser().getId())
                .roles(principalDetails.getUser().getRoleList())
                .expiration(jwtProperties.getRefreshTokenTime()) // 7일 후 만료
                .build();
        refreshTokenRepository.save(refreshTokenEntity);

        response.addHeader(jwtProperties.getHeaderString(), jwtProperties.getTokenPrefix() + jwtToken);
        response.addHeader("Refresh-Token", refreshToken);
    }
}