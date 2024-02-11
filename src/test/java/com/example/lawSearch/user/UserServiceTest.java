package com.example.lawSearch.user;

import com.example.lawSearch.domain.user.dto.request.UserRequestDto;
import com.example.lawSearch.domain.user.dto.response.UserResponseDto;
import com.example.lawSearch.domain.user.exception.EmailExistException;
import com.example.lawSearch.domain.user.model.User;
import com.example.lawSearch.domain.user.service.UserService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    @Transactional
    @DisplayName("회원가입")
    void join() {
        UserRequestDto request = new UserRequestDto("email@naver.com", "password", "kim", 20, true);
        userService.join(request);
        User user = userService.findByEmail(request.getEmail());

        assertThat(user.getEmail()).isEqualTo(request.getEmail());
        assertThat(user.getName()).isEqualTo(request.getName());
        assertThat(user.getAge()).isEqualTo(request.getAge());
        assertThat(user.isSex()).isEqualTo(request.isSex());
    }

    @Test
    @Transactional
    @DisplayName("회원가입 시 이메일 중복")
    void duplicationEmail() {
        UserRequestDto request = new UserRequestDto("email@naver.com", "password", "kim", 20, true);
        userService.join(request);

        UserRequestDto request2 = new UserRequestDto("email@naver.com", "pw", "lee", 30, false);

        assertThrows(EmailExistException.class, () -> userService.join(request2), "이메일 중복입니다.");
    }

    @Test
    @Transactional
    @DisplayName("비밀번호 변경")
    void changePassword() {
        UserRequestDto request = new UserRequestDto("email@naver.com", "password", "kim", 20, true);
        userService.join(request);
        User user = userService.findByEmail("email@naver.com");

        userService.updatePassword(user.getId(), request.getPassword(), "newPassword");
        boolean changed = bCryptPasswordEncoder.matches("newPassword", user.getPassword());
        assertTrue(changed);
    }
}
