package com.example.lawSearch.domain.answer.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AnswerRequestDto {
    @NotBlank(message = "답변을 입력해주세요.")
    private String content;
}
