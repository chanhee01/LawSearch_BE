package com.example.lawSearch.domain.question.dto.request;

import com.example.lawSearch.global.base.category.Category;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateQuestionRequest {
    @NotBlank(message = "제목을 입력해주세요.")
    private String title;
    @NotBlank(message = "내용을 입력해주세요.")
    private String content;
    @NotBlank(message = "위원회를 입력해주세요.")
    private String category;

    public Category getCategory() {
        return Category.categoryConverter(category);
    }
}
