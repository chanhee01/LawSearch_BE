package com.example.lawSearch.domain.suggestion.repository;

import com.example.lawSearch.domain.suggestion.model.Suggestion;
import com.example.lawSearch.domain.user.model.User;
import com.example.lawSearch.global.base.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SuggestionRepository extends JpaRepository<Suggestion, Long> {
    List<Suggestion> findAllByUser(User user);
    List<Suggestion> findAllSuggestion(Category category, Boolean likeCount);
    List<Suggestion> findAllByCategory(Category category);
}
