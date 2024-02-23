package com.example.lawSearch.domain.suggestion.dto.response;

import com.example.lawSearch.domain.suggestion.model.Suggestion;
import com.example.lawSearch.global.base.category.Category;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class SuggestionListResponse {
    private String title;
    private Category category;
    private String name;
    private LocalDateTime createdTime;
    private Integer likeCount;
    private Boolean likeStatus; // 내가 이 글에 좋아요를 눌렀는지 여부
    private Long id;

    public static SuggestionListResponse convert(Suggestion suggestion, List<Long> userLikeSuggestion) {
        return SuggestionListResponse.builder()
                .title(suggestion.getTitle())
                .category(suggestion.getCategory())
                .name(suggestion.getUser().getName())
                .createdTime(suggestion.getCreatedDate())
                .likeCount(suggestion.getLikeList() != null ? suggestion.getLikeList().size() : 0)
                .likeStatus(userLikeSuggestion.contains(suggestion.getId()) ? true : false)
                .id(suggestion.getId())
                .build();
    }

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
