package com.example.lawSearch.domain.question.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteQuestionRequest {
    @NotNull(message = "삭제하실 질문의 id를 입력해주세요.")
    private Long questionId;
}
