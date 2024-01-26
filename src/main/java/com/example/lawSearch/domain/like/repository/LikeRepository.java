package com.example.lawSearch.domain.like.repository;

import com.example.lawSearch.domain.like.model.Like;
import com.example.lawSearch.domain.suggestion.model.Suggestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Like findLikeByUserIdAndSuggestion(Long userId, Suggestion suggestion);
}
