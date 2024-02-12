package com.example.lawSearch.domain.user.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckCertificationRequestDto {

    @NotBlank
    private Long certificationId;

    @NotBlank
    private String certificationNumber;
}
