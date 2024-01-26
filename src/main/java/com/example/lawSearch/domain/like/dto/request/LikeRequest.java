package com.example.lawSearch.domain.like.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LikeRequest {
    @NotNull(message = "정책 건의 id를 입력해주세요.")
    private Long suggestionId;
}
