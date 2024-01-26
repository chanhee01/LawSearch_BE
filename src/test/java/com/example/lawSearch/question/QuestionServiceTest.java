package com.example.lawSearch.question;

import com.example.lawSearch.domain.question.dto.request.CreateQuestionRequest;
import com.example.lawSearch.domain.question.dto.response.QuestionListResponse;
import com.example.lawSearch.domain.question.exception.QuestionNotFoundException;
import com.example.lawSearch.domain.question.model.Question;
import com.example.lawSearch.domain.question.service.QuestionService;
import com.example.lawSearch.domain.user.dto.request.UserRequestDto;
import com.example.lawSearch.domain.user.model.User;
import com.example.lawSearch.domain.user.service.UserService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
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
        CreateQuestionRequest request = new CreateQuestionRequest("title", "content", "교육위원회");
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
        CreateQuestionRequest request = new CreateQuestionRequest("title", "content", "교육위원회");
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
        CreateQuestionRequest request = new CreateQuestionRequest("title", "content", "교육위원회");
        UserRequestDto userDto = new UserRequestDto("email@naver.com", "password", "kim", 20, true);
        userService.join(userDto);
        User user = userService.findByEmail(userDto.getEmail());
        Long questionId = questionService.createQuestion(request, user);

        questionService.deleteQuestion(questionId, user);

        assertThrows(QuestionNotFoundException.class, () -> questionService.findById(questionId), "문의가 삭제되었습니다.");
    }

    @Test
    @Transactional
    @DisplayName("자기가 작성한 문의 글 리스트")
    void questionList() {
        CreateQuestionRequest request1 = new CreateQuestionRequest("title1", "content1", "교육위원회");
        CreateQuestionRequest request2 = new CreateQuestionRequest("title2", "content2", "교육위원회");
        CreateQuestionRequest request3 = new CreateQuestionRequest("title3", "content3", "교육위원회");

        UserRequestDto userDto = new UserRequestDto("email@naver.com", "password", "kim", 20, true);
        userService.join(userDto);

        User user = userService.findByEmail(userDto.getEmail());
        Long questionId1 = questionService.createQuestion(request1, user);
        Long questionId2 = questionService.createQuestion(request2, user);
        Long questionId3 = questionService.createQuestion(request3, user);

        Question question1 = questionService.findById(questionId1);
        Question question2 = questionService.findById(questionId2);
        Question question3 = questionService.findById(questionId3);

        // 다른 사람이 작성한 글은 포함이 되지 않는다.
        UserRequestDto userDto2 = new UserRequestDto("aaa@naver.com", "password2", "lee", 30, false);
        userService.join(userDto2);

        User user2 = userService.findByEmail(userDto2.getEmail());
        CreateQuestionRequest request4 = new CreateQuestionRequest("title4", "content4", "교육위원회");
        Long questionId4 = questionService.createQuestion(request4, user2);
        Question question4 = questionService.findById(questionId4);
        // ===== 다른 사람이 작성한 문의 글 ======

        List<QuestionListResponse> questionList = questionService.findAllByUser(user);

        assertThat(questionList.contains(question1));
        assertThat(questionList.contains(question2));
        assertThat(questionList.contains(question3));
        assertThat(!questionList.contains(question4));
    }
}
