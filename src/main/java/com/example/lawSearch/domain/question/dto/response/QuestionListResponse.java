package com.example.lawSearch.domain.question.dto.response;

import com.example.lawSearch.domain.question.model.Question;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class QuestionListResponse {
    private String title;
    private String category; // enum으로 수정 필요
    private LocalDateTime createdTime;

    public static QuestionListResponse convert(Question question) {
        return QuestionListResponse.builder()
                .title(question.getTitle())
                .category(question.getCategory())
                .createdTime(question.getCreatedDate()).build();
    }
}
