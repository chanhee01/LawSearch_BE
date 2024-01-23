package com.example.lawSearch.domain.suggestion.service;

import com.example.lawSearch.domain.suggestion.dto.request.CreateSuggestionDto;
import com.example.lawSearch.domain.suggestion.model.Suggestion;
import com.example.lawSearch.domain.suggestion.repository.SuggestionRepository;
import com.example.lawSearch.domain.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SuggestionService {

    private final SuggestionRepository suggestionRepository;

    @Transactional
    public Long createSuggestion(CreateSuggestionDto request, User user) {
        Suggestion suggestion = Suggestion.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .category(request.getCategory())
                .user(user).build();

        Suggestion save = suggestionRepository.save(suggestion);
        return save.getId();
    }
}
