package com.example.lawSearch.domain.suggestion.dto.response;

import com.example.lawSearch.domain.suggestion.model.Suggestion;
import com.example.lawSearch.global.base.category.Category;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class SuggestionResponse {
    private String title;
    private String content;
    private Category category;
    private LocalDateTime createdTime;
    private String name;
    private Integer likeCount;

    public SuggestionResponse(Suggestion suggestion) {
        this.title = suggestion.getTitle();
        this.content = suggestion.getContent();
        this.category = suggestion.getCategory();
        this.createdTime = suggestion.getCreatedDate();
        this.name = suggestion.getUser().getName();
        this.likeCount = (suggestion.getLikeList() != null) ? suggestion.getLikeList().size() : 0;
    }
}
