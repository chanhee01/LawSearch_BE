package com.example.lawSearch.domain.user.api;

import com.example.lawSearch.domain.user.dto.request.UserRequestDto;
import com.example.lawSearch.domain.user.dto.request.ValidationEmailRequestDto;
import com.example.lawSearch.domain.user.dto.response.UserResponseDto;
import com.example.lawSearch.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    public ResponseEntity<UserResponseDto> join(@RequestBody UserRequestDto request) {
        UserResponseDto response = userService.join(request);
        return ResponseEntity.ok(response);
    }

    // 이메일 중복확인 버튼, true면 이미 존재
    @PostMapping("validation/email")
    public ResponseEntity<Boolean> validationEmail(@RequestBody ValidationEmailRequestDto request) {
        return ResponseEntity.ok(userService.validationEmail(request.getEmail()));
    }
}
