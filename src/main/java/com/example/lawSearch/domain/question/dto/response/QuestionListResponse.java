package com.example.lawSearch.domain.question.dto.response;

import com.example.lawSearch.domain.question.model.Question;
import com.example.lawSearch.global.base.category.Category;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class QuestionListResponse {
    private String title;
    private Category category;
    private String name;
    private LocalDateTime createdTime;
    private boolean status;
    private Long id;

    public static QuestionListResponse convert(Question question) {
        return QuestionListResponse.builder()
                .title(question.getTitle())
                .category(question.getCategory())
                .name(question.getUser().getName())
                .createdTime(question.getCreatedDate())
                .status(question.isStatus())
                .id(question.getId())
                .build();
    }
}
