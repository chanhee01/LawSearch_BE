package com.example.lawSearch.question;

import com.example.lawSearch.domain.question.dto.request.CreateQuestionRequest;
import com.example.lawSearch.domain.question.model.Question;
import com.example.lawSearch.domain.question.service.QuestionService;
import com.example.lawSearch.domain.user.dto.request.UserRequestDto;
import com.example.lawSearch.domain.user.model.User;
import com.example.lawSearch.domain.user.service.UserService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

        Assertions.assertEquals(request.getTitle(), question.getTitle());
        Assertions.assertEquals(request.getContent(), question.getContent());
        Assertions.assertEquals(request.getCategory(), question.getCategory());
    }
}
