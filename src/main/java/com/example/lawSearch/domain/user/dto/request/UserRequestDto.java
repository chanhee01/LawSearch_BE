package com.example.lawSearch.domain.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {

    private String email;
    private String password;
    private String name;
    private Integer age;
    private boolean sex;
}
