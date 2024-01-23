package com.example.lawSearch.domain.suggestion.dto.response;

import com.example.lawSearch.domain.suggestion.model.Suggestion;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class SuggestionListResponse {
    private String title;
    private String category;
    private LocalDateTime createdTime;
    private Long id;

    public static SuggestionListResponse convert(Suggestion suggestion) {
        return SuggestionListResponse.builder()
                .title(suggestion.getTitle())
                .category(suggestion.getTitle())
                .createdTime(suggestion.getCreatedDate())
                .id(suggestion.getId())
                .build();
    }
}
