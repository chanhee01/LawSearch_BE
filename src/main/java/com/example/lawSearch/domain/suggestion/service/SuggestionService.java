package com.example.lawSearch.domain.suggestion.service;

import com.example.lawSearch.domain.like.model.Like;
import com.example.lawSearch.domain.like.service.LikeService;
import com.example.lawSearch.domain.suggestion.dto.request.CreateSuggestionDto;
import com.example.lawSearch.domain.suggestion.dto.response.SuggestionListResponse;
import com.example.lawSearch.domain.suggestion.exception.SuggestionNotFoundException;
import com.example.lawSearch.domain.suggestion.exception.SuggestionUserMismatchException;
import com.example.lawSearch.domain.suggestion.model.Suggestion;
import com.example.lawSearch.domain.suggestion.repository.SuggestionRepository;
import com.example.lawSearch.domain.user.model.User;
import com.example.lawSearch.global.base.category.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SuggestionService {

    private final SuggestionRepository suggestionRepository;
    private final LikeService likeService;

    @Transactional
    public Long createSuggestion(CreateSuggestionDto request, User user) {
        Suggestion suggestion = Suggestion.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .category(request.getCategory())
                .user(user).build();

        Suggestion save = suggestionRepository.save(suggestion);
        return save.getId();
    }

    @Transactional
    public void deleteSuggestion(Long suggestionId, User user) {
        Suggestion suggestion = findById(suggestionId);

        if (user.getId() != suggestion.getUser().getId()) {
            throw new SuggestionUserMismatchException(user.getId());
        }

        suggestionRepository.delete(suggestion);
    }

    public List<SuggestionListResponse> findAllSuggestion(String reqCategory, Boolean likeCount, User user) {
        Category category;
        if (reqCategory != null) category = Category.categoryConverter(reqCategory);
        else category = null;

        List<Suggestion> suggestionList = suggestionRepository.findAllSuggestion(category, likeCount);

        List<Long> userLikeSuggestions = suggestionUserLikes(suggestionList, user);

        return suggestionList.stream()
                .map(suggestion -> SuggestionListResponse.convert(suggestion, userLikeSuggestions))
                .collect(Collectors.toList());
    }

    public List<SuggestionListResponse> findAllByUser(User user) {
        List<Suggestion> suggestionList = suggestionRepository.findAllByUser(user);

        List<SuggestionListResponse> suggestions = suggestionList.stream()
                .map((suggestion -> SuggestionListResponse.convert(suggestion)))
                .collect(Collectors.toList());

        return suggestions;
    }

    public Suggestion findById(Long suggestionId) {
        Suggestion suggestion = suggestionRepository.findById(suggestionId)
                .orElseThrow(() -> new SuggestionNotFoundException(suggestionId));
        return suggestion;
    }

    public List<SuggestionListResponse> findByCategory(User user) {
        Category category = Category.categoryConverter(user.getName());
        List<Suggestion> suggestionList = suggestionRepository.findAllByCategory(category);

        List<Long> userLikeSuggestions = suggestionUserLikes(suggestionList, user);

        List<SuggestionListResponse> suggestions = suggestionList.stream()
                .map((suggestion -> SuggestionListResponse.convert(suggestion, userLikeSuggestions)))
                .collect(Collectors.toList());
        return suggestions;
    }

    public List<Long> suggestionUserLikes(List<Suggestion> suggestionList, User user) {
        List<Long> suggestionIds = suggestionList.stream()
                .map((suggestion -> suggestion.getId()))
                .collect(Collectors.toList());

        List<Like> likes = likeService.likeListBySuggestion(user, suggestionIds);

        return likes.stream().
                map(like -> like.getSuggestion().getId())
                .collect(Collectors.toList());
    }
}
