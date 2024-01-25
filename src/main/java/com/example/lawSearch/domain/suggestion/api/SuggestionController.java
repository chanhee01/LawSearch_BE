package com.example.lawSearch.domain.suggestion.api;

import com.example.lawSearch.domain.suggestion.dto.request.CreateSuggestionDto;
import com.example.lawSearch.domain.suggestion.dto.response.SuggestionIdResponse;
import com.example.lawSearch.domain.suggestion.dto.response.SuggestionListResponse;
import com.example.lawSearch.domain.suggestion.dto.response.SuggestionResponse;
import com.example.lawSearch.domain.suggestion.model.Suggestion;
import com.example.lawSearch.domain.suggestion.service.SuggestionService;
import com.example.lawSearch.global.auth.PrincipalDetails;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/suggestion")
public class SuggestionController {

    private final SuggestionService suggestionService;

    @PostMapping("")
    public ResponseEntity<SuggestionIdResponse> createSuggestion(
            @Valid @RequestBody CreateSuggestionDto request, @AuthenticationPrincipal PrincipalDetails principal) {
        Long suggestionId = suggestionService.createSuggestion(request, principal.getUser());
        return ResponseEntity.ok(new SuggestionIdResponse(suggestionId));
    }

    @GetMapping("/mySuggestion")
    public ResponseEntity<List<SuggestionListResponse>> mySuggestion(
            @AuthenticationPrincipal PrincipalDetails principal) {
        List<SuggestionListResponse> suggestions = suggestionService.findAllByUser(principal.getUser());
        return ResponseEntity.ok(suggestions);
    }

    @DeleteMapping("/{questionId}")
    public ResponseEntity<Void> deleteSuggestion(
            @AuthenticationPrincipal PrincipalDetails principal, @PathVariable Long questionId) {
        suggestionService.deleteSuggestion(questionId, principal.getUser());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/list")
    public ResponseEntity<List<SuggestionListResponse>> suggestionList(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Boolean likeCount) {

        List<SuggestionListResponse> allSuggestion = suggestionService.findAllSuggestion(category, likeCount);
        return ResponseEntity.ok(allSuggestion);
    }

    @GetMapping("/{questionId}")
    public ResponseEntity<SuggestionResponse> findOne(@PathVariable Long questionId) {
        Suggestion suggestion = suggestionService.findById(questionId);
        return ResponseEntity.ok(new SuggestionResponse(suggestion));
    }
}
