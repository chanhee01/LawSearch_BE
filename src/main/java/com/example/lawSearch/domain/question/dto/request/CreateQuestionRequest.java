package com.example.lawSearch.domain.question.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateQuestionRequest {
    private String title;
    private String content;
    private String category; // enum으로 수정필요
}
