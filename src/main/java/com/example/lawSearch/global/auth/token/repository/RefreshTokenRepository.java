package com.example.lawSearch.global.auth.token.repository;

import com.example.lawSearch.global.auth.token.model.RefreshToken;
import org.springframework.data.repository.CrudRepository;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {
}
