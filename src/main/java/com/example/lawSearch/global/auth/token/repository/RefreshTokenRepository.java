package com.example.lawSearch.global.auth.token.repository;

import com.example.lawSearch.global.auth.token.model.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, String> {
}
