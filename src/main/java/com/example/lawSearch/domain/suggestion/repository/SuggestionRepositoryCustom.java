package com.example.lawSearch.domain.suggestion.repository;

import com.example.lawSearch.domain.suggestion.model.Suggestion;
import com.example.lawSearch.global.base.category.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SuggestionRepositoryCustom {
    Page<Suggestion> findAllSuggestion(Category category, Boolean likeCount, Pageable pageable);
}
