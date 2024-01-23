package com.example.lawSearch.domain.suggestion.repository;

import com.example.lawSearch.domain.suggestion.model.Suggestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SuggestionRepository extends JpaRepository<Suggestion, Long> {
}
