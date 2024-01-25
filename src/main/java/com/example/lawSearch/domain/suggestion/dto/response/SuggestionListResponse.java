package com.example.lawSearch.domain.suggestion.dto.response;

import com.example.lawSearch.domain.suggestion.model.Suggestion;
import com.example.lawSearch.global.base.category.Category;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class SuggestionListResponse {
    private String title;
    private Category category;
    private String name;
    private LocalDateTime createdTime;
    private Integer likeCount;
    private Long id;

    public static SuggestionListResponse convert(Suggestion suggestion) {
        return SuggestionListResponse.builder()
                .title(suggestion.getTitle())
                .category(suggestion.getCategory())
                .name(suggestion.getUser().getName())
                .createdTime(suggestion.getCreatedDate())
                .likeCount(suggestion.getLikeList() != null ? suggestion.getLikeList().size() : 0)
                .id(suggestion.getId())
                .build();
    }
}
