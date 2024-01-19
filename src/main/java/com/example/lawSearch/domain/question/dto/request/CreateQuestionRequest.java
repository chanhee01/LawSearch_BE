package com.example.lawSearch.domain.question.dto.request;

import com.example.lawSearch.global.base.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateQuestionRequest {
    private String title;
    private String content;
    private Category category;
}
