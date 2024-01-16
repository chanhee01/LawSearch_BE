package com.example.lawSearch.domain.question.api;

import com.example.lawSearch.domain.question.dto.request.CreateQuestionRequest;
import com.example.lawSearch.domain.question.dto.response.CreateQuestionResponse;
import com.example.lawSearch.domain.question.service.QuestionService;
import com.example.lawSearch.domain.user.model.User;
import com.example.lawSearch.global.auth.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/question")
public class QuestionController {

    private final QuestionService questionService;

    @PostMapping("")
    public ResponseEntity<CreateQuestionResponse> createQuestion (
            Authentication authentication, @RequestBody CreateQuestionRequest request) {
        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        User user = principal.getUser();
        Long question = questionService.createQuestion(request, user);

        return ResponseEntity.ok(new CreateQuestionResponse(question));
    }


}
