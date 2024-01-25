package com.example.lawSearch.domain.user.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidationEmailRequestDto {
    @NotBlank(message = "이메일을 입력해주세요.")
    private String email;
}
