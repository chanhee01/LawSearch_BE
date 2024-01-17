package com.example.lawSearch.question;

import com.example.lawSearch.domain.question.dto.request.CreateQuestionRequest;
import com.example.lawSearch.domain.question.exception.QuestionNotFoundException;
import com.example.lawSearch.domain.question.model.Question;
import com.example.lawSearch.domain.question.service.QuestionService;
import com.example.lawSearch.domain.user.dto.request.UserRequestDto;
import com.example.lawSearch.domain.user.exception.EmailExistException;
import com.example.lawSearch.domain.user.model.User;
import com.example.lawSearch.domain.user.service.UserService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class QuestionServiceTest {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private UserService userService;

    @Test
    @Transactional
    @DisplayName("문의 글 작성")
    void createQuestion() {
        CreateQuestionRequest request = new CreateQuestionRequest("title", "content", "category");
        UserRequestDto userDto = new UserRequestDto("email@naver.com", "password", "kim", 20, true);
        userService.join(userDto);
        User user = userService.findByEmail(userDto.getEmail());
        Long questionId = questionService.createQuestion(request, user);

        Question question = questionService.findById(questionId);

        assertEquals(request.getTitle(), question.getTitle());
        assertEquals(request.getContent(), question.getContent());
        assertEquals(request.getCategory(), question.getCategory());
    }

    @Test
    @Transactional
    @DisplayName("문의 글 수정")
    void updateQuestion() {
        CreateQuestionRequest request = new CreateQuestionRequest("title", "content", "category");
        UserRequestDto userDto = new UserRequestDto("email@naver.com", "password", "kim", 20, true);
        userService.join(userDto);
        User user = userService.findByEmail(userDto.getEmail());
        Long questionId = questionService.createQuestion(request, user);

        Question question = questionService.findById(questionId);

        question.updateQuestion("new title", "new content");

        assertEquals(question.getTitle(), "new title");
        assertEquals(question.getContent(), "new content");
    }

    @Test
    @Transactional
    @DisplayName("문의 글 삭제")
    void deleteQuestion() {
        CreateQuestionRequest request = new CreateQuestionRequest("title", "content", "category");
        UserRequestDto userDto = new UserRequestDto("email@naver.com", "password", "kim", 20, true);
        userService.join(userDto);
        User user = userService.findByEmail(userDto.getEmail());
        Long questionId = questionService.createQuestion(request, user);

        questionService.deleteQuestion(questionId, user);

        assertThrows(QuestionNotFoundException.class, () -> questionService.findById(questionId), "문의가 삭제되었습니다.");
    }
}
