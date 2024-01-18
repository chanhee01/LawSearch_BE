package com.example.lawSearch.domain.question.dto.response;

import com.example.lawSearch.domain.question.model.Question;
import com.example.lawSearch.global.base.Category;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class QuestionListResponse {
    private String title;
    private Category category;
    private LocalDateTime createdTime;

    public static QuestionListResponse convert(Question question) {
        return QuestionListResponse.builder()
                .title(question.getTitle())
                .category(question.getCategory())
                .createdTime(question.getCreatedDate()).build();
    }
}
