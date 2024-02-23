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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

    @GetMapping("/{suggestionId}")
    public ResponseEntity<SuggestionResponse> findOne(@PathVariable Long suggestionId) {
        Suggestion suggestion = suggestionService.findById(suggestionId);
        return ResponseEntity.ok(new SuggestionResponse(suggestion));
    }

    @DeleteMapping("/{suggestionId}")
    public ResponseEntity<Void> deleteSuggestion(
            @AuthenticationPrincipal PrincipalDetails principal, @PathVariable Long suggestionId) {
        suggestionService.deleteSuggestion(suggestionId, principal.getUser());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/mySuggestion")
    public ResponseEntity<List<SuggestionListResponse>> mySuggestion(
            @AuthenticationPrincipal PrincipalDetails principal) {
        List<SuggestionListResponse> suggestions = suggestionService.findAllByUser(principal.getUser());
        return ResponseEntity.ok(suggestions);
    }

    @GetMapping("/list")
    public ResponseEntity<Page<SuggestionListResponse>> suggestionList(
            @AuthenticationPrincipal PrincipalDetails principal,
            @RequestParam(name = "category", required = false, value = "category") String category,
            @RequestParam(name = "likeCount", required = false) Boolean likeCount,
            @PageableDefault Pageable pageable) {

        Page<SuggestionListResponse> allSuggestion = suggestionService
                .findAllSuggestion(category, likeCount, principal.getUser(), pageable);
        return ResponseEntity.ok(allSuggestion);
    }
}
