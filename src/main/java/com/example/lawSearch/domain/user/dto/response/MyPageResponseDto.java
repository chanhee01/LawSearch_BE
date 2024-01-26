package com.example.lawSearch.domain.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyPageResponseDto {
    private Integer questionCount;
    private Integer suggestionCount;
}
