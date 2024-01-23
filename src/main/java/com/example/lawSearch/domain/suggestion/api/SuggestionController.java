package com.example.lawSearch.domain.suggestion.api;

import com.example.lawSearch.domain.suggestion.dto.request.CreateSuggestionDto;
import com.example.lawSearch.domain.suggestion.dto.response.SuggestionIdResponse;
import com.example.lawSearch.domain.suggestion.service.SuggestionService;
import com.example.lawSearch.global.auth.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/suggestion")
public class SuggestionController {

    private final SuggestionService suggestionService;

    @PostMapping("/")
    public ResponseEntity<SuggestionIdResponse> createSuggestion(
            @RequestBody CreateSuggestionDto request, @AuthenticationPrincipal PrincipalDetails principal) {
        Long suggestionId = suggestionService.createSuggestion(request, principal.getUser());
        return ResponseEntity.ok(new SuggestionIdResponse(suggestionId));
    }

    @DeleteMapping("/{questionId}")
    public ResponseEntity<Void> deleteSuggestion(
            @AuthenticationPrincipal PrincipalDetails principal, @PathVariable Long questionId) {
        suggestionService.
    }
}
