package com.example.lawSearch.domain.user.api;

import com.example.lawSearch.domain.user.dto.request.CheckCertificationRequestDto;
import com.example.lawSearch.domain.user.dto.request.EmailCertificationRequestDto;
import com.example.lawSearch.domain.user.dto.request.UserRequestDto;
import com.example.lawSearch.domain.user.dto.request.ValidationEmailRequestDto;
import com.example.lawSearch.domain.user.dto.response.EmailCertificationResponseDto;
import com.example.lawSearch.domain.user.dto.response.MyPageResponseDto;
import com.example.lawSearch.domain.user.dto.response.UserResponseDto;
import com.example.lawSearch.domain.user.service.UserService;
import com.example.lawSearch.global.auth.PrincipalDetails;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    public ResponseEntity<UserResponseDto> join(@Valid @RequestBody UserRequestDto request) {
        UserResponseDto response = userService.join(request);
        return ResponseEntity.ok(response);
    }

    // 이메일 중복확인 버튼, true면 이미 존재
    @PostMapping("validation/email")
    public ResponseEntity<Boolean> validationEmail(@Valid @RequestBody ValidationEmailRequestDto request) {
        return ResponseEntity.ok(userService.validationEmail(request.getEmail()));
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/myPage")
    public ResponseEntity<MyPageResponseDto> myPage(@AuthenticationPrincipal PrincipalDetails principal) {
        MyPageResponseDto myPageResponse = userService.myPage(principal.getUser());
        return ResponseEntity.ok(myPageResponse);
    }

    @PostMapping("/email/certification")
    public ResponseEntity<EmailCertificationResponseDto> emailCertification(
            @Valid @RequestBody EmailCertificationRequestDto request) {
        EmailCertificationResponseDto certificationId = userService.emailCertification(request);
        return ResponseEntity.ok(certificationId);
    }

    @PostMapping("/check/certification")
    public ResponseEntity<Boolean> certificationCheck(
        @Valid @RequestBody CheckCertificationRequestDto request) {
        Boolean checkedStatus = userService.checkCertification(request);
        return ResponseEntity.ok(checkedStatus);
    }
}
