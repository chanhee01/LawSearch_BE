package com.example.lawSearch.domain.question.dto.response;

import com.example.lawSearch.domain.question.model.Question;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class QuestionListResponse {
    private String title;
    private String category;
    private String name;
    private LocalDateTime createdTime;
    private boolean status;
    private Long id;

    public static QuestionListResponse convert(Question question) {
        return QuestionListResponse.builder()
                .title(question.getTitle())
                .category(question.getTitle())
                .name(question.getUser().getName())
                .createdTime(question.getCreatedDate())
                .status(question.isStatus())
                .id(question.getId())
                .build();
    }
}
