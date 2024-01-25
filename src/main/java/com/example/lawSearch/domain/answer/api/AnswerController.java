package com.example.lawSearch.domain.answer.api;

import com.example.lawSearch.domain.answer.service.AnswerService;
import com.example.lawSearch.domain.question.dto.response.QuestionListResponse;
import com.example.lawSearch.domain.question.service.QuestionService;
import com.example.lawSearch.domain.suggestion.dto.response.SuggestionListResponse;
import com.example.lawSearch.domain.suggestion.service.SuggestionService;
import com.example.lawSearch.global.auth.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/answer")
public class AnswerController {

    private final AnswerService answerService;
    private final QuestionService questionService;
    private final SuggestionService suggestionService;

    // 국회의원 인증은 내가 할 수 없기 때문에 인증 기능 없이 사용자 이름 = 위원회로 지정, 관리자 계정은 수동으로 만든다.
    @GetMapping("/questionList")
        public ResponseEntity<List<QuestionListResponse>> questionList(
            @AuthenticationPrincipal PrincipalDetails principal) {
        List<QuestionListResponse> questionList = questionService.findByCategory(principal.getUser());
        return ResponseEntity.ok(questionList);
    }

    @GetMapping("/suggestionList")
    public ResponseEntity<List<SuggestionListResponse>> suggestionList(
            @AuthenticationPrincipal PrincipalDetails principal) {
        List<SuggestionListResponse> suggestionList = suggestionService.findByCategory(principal.getUser());
        return ResponseEntity.ok(suggestionList);
    }
}
