package com.example.lawSearch.domain.suggestion.repository;

import com.example.lawSearch.domain.suggestion.model.Suggestion;
import com.example.lawSearch.global.base.category.Category;

import java.time.LocalDateTime;
import java.util.List;

public interface SuggestionRepositoryCustom {
    List<Suggestion> findAllSuggestion(Category category, Boolean likeCount);
}
