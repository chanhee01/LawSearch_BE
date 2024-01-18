package com.example.lawSearch.domain.question.api;

import com.example.lawSearch.domain.answer.model.Answer;
import com.example.lawSearch.domain.answer.service.AnswerService;
import com.example.lawSearch.domain.question.dto.request.CreateQuestionRequest;
import com.example.lawSearch.domain.question.dto.request.UpdateQuestionRequest;
import com.example.lawSearch.domain.question.dto.response.QuestionListResponse;
import com.example.lawSearch.domain.question.dto.response.QuestionIdResponse;
import com.example.lawSearch.domain.question.dto.response.QuestionResponse;
import com.example.lawSearch.domain.question.model.Question;
import com.example.lawSearch.domain.question.service.QuestionService;
import com.example.lawSearch.domain.user.model.User;
import com.example.lawSearch.global.auth.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/question")
public class QuestionController {

    private final QuestionService questionService;
    private final AnswerService answerService;

    @PostMapping("")
    public ResponseEntity<QuestionIdResponse> createQuestion (
            Authentication authentication, @RequestBody CreateQuestionRequest request) {
        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        User user = principal.getUser();
        Long questionId = questionService.createQuestion(request, user);

        return ResponseEntity.ok(new QuestionIdResponse(questionId));
    }

    @GetMapping("/{questionId}")
    public ResponseEntity<QuestionResponse> getQuestion(Authentication authentication, @PathVariable Long questionId) {
        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        User user = principal.getUser();
        Question question = questionService.findById(questionId);
        Answer answer = answerService.findByQuestionId(question.getId());

        return ResponseEntity.ok(new QuestionResponse(question, answer));
    }

    @PatchMapping("/{questionId}")
    public ResponseEntity<QuestionIdResponse> updateQuestion (
            Authentication authentication, @RequestBody UpdateQuestionRequest request,
            @PathVariable Long questionId) {
        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        User user = principal.getUser();
        questionService.updateQuestion(request, questionId, user);

        return ResponseEntity.ok(new QuestionIdResponse(questionId));
    }

    @DeleteMapping("/{questionId}")
    public ResponseEntity<Void> deleteQuestion (
            Authentication authentication, @PathVariable Long questionId) {
        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        User user = principal.getUser();
        questionService.deleteQuestion(questionId, user);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/list")
    public ResponseEntity<List<QuestionListResponse>> questionList(Authentication authentication) {
        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        User user = principal.getUser();
        List<QuestionListResponse> questionList = questionService.findAllByUser(user.getId());

        return ResponseEntity.ok(questionList);
    }

}
