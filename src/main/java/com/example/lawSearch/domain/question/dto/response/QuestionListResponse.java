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
    private String category;
    private LocalDateTime createdTime;
    private boolean status;

    public static QuestionListResponse convert(Question question) {
        return QuestionListResponse.builder()
                .title(question.getTitle())
                .category(question.getTitle())
                .createdTime(question.getCreatedDate())
                .status(question.isStatus())
                .build();
    }
}
