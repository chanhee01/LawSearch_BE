package com.example.lawSearch.answer;

import com.example.lawSearch.domain.answer.dto.request.AnswerRequestDto;
import com.example.lawSearch.domain.answer.model.Answer;
import com.example.lawSearch.domain.answer.service.AnswerService;
import com.example.lawSearch.domain.question.dto.request.CreateQuestionRequest;
import com.example.lawSearch.domain.question.dto.request.UpdateQuestionRequest;
import com.example.lawSearch.domain.question.exception.QuestionHasAnswerException;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class AnswerServiceTest {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private UserService userService;

    @Autowired
    private AnswerService answerService;

    @Test
    @DisplayName("문의글에 답변하기")
    void answer() {
        CreateQuestionRequest request = new CreateQuestionRequest("title", "content", "교육위원회");
        UserRequestDto userDto = new UserRequestDto("email@naver.com", "password", "kim", 20, true);
        userService.join(userDto);
        User user = userService.findByEmail(userDto.getEmail());
        Long questionId = questionService.createQuestion(request, user);

        Question question = questionService.findById(questionId);

        UserRequestDto userDto2 = new UserRequestDto("email2@naver.com", "password2", "교육위원회", 20, true);
        userService.join(userDto2);
        User user2 = userService.findByEmail(userDto.getEmail());

        AnswerRequestDto answerRequest = new AnswerRequestDto("answer");
        answerService.save(user2, questionId, answerRequest.getContent());

        Answer answer = answerService.findByQuestionId(question.getId());

        assertEquals(answer.getContent(), answerRequest.getContent());

        // 답변이 완료된 문의는 수정할 수 없다.
        UpdateQuestionRequest updateRequest = new UpdateQuestionRequest("new title", "new content");
        assertThrows(QuestionHasAnswerException.class,
                () -> questionService.updateQuestion(updateRequest, questionId, user), "");
    }
}
