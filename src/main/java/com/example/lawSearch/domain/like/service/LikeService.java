package com.example.lawSearch.domain.like.service;

import com.example.lawSearch.domain.like.exception.AlreadyLikeException;
import com.example.lawSearch.domain.like.exception.LikeNotFoundException;
import com.example.lawSearch.domain.like.exception.SelfLikeException;
import com.example.lawSearch.domain.like.model.Like;
import com.example.lawSearch.domain.like.repository.LikeRepository;
import com.example.lawSearch.domain.suggestion.dto.response.SuggestionResponse;
import com.example.lawSearch.domain.suggestion.exception.SuggestionNotFoundException;
import com.example.lawSearch.domain.suggestion.model.Suggestion;
import com.example.lawSearch.domain.suggestion.repository.SuggestionRepository;
import com.example.lawSearch.domain.suggestion.service.SuggestionService;
import com.example.lawSearch.domain.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LikeService {

    private final LikeRepository likeRepository;
    private final SuggestionRepository suggestionRepository;

    @Transactional
    public void like(User user, Long suggestionId) {
        Suggestion suggestion = findById(suggestionId);

        if (user.getId() == suggestion.getUser().getId()) {
            throw new SelfLikeException(suggestionId);
        }

        if (likeRepository.findLikeByUserIdAndSuggestion(user.getId(), suggestion) != null) {
            throw new AlreadyLikeException(suggestionId);
        }

        Like like = Like.builder()
                .user(user)
                .suggestion(suggestion).build();
        likeRepository.save(like);
        suggestion.addLike(like);
    }

    @Transactional
    public void deleteLike(User user, Long suggestionId) {
        Suggestion suggestion = findById(suggestionId);

        if (likeRepository.findLikeByUserIdAndSuggestion(user.getId(), suggestion) == null) {
            throw new LikeNotFoundException(suggestionId);
        }

        Like like = likeRepository.findLikeByUserIdAndSuggestion(user.getId(), suggestion);

        likeRepository.delete(like);
        suggestion.deleteLike(like);
    }

    public List<Like> likeListBySuggestion(User user, List<Long> suggestions) {
        return likeRepository.findByUserIdAndSuggestionIdIn(user.getId(), suggestions);
    }

    public Suggestion findById(Long suggestionId) {
        return suggestionRepository.findById(suggestionId).orElseThrow(() -> new SuggestionNotFoundException(suggestionId));
    }
}
