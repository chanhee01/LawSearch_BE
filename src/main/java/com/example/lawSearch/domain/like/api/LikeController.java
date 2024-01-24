package com.example.lawSearch.domain.like.api;

import com.example.lawSearch.domain.like.dto.request.LikeRequest;
import com.example.lawSearch.domain.like.service.LikeService;
import com.example.lawSearch.global.auth.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/like")
public class LikeController {

    private final LikeService likeService;

    @PostMapping("")
    public ResponseEntity<Void> like(
            @AuthenticationPrincipal PrincipalDetails principal, @RequestBody LikeRequest request) {
        likeService.like(principal.getUser(), request.getSuggestionId());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("")
    public ResponseEntity<Void> deleteLike(
            @AuthenticationPrincipal PrincipalDetails principal, @RequestBody LikeRequest request) {
        likeService.deleteLike(principal.getUser(), request.getSuggestionId());
        return ResponseEntity.ok().build();
    }
}
