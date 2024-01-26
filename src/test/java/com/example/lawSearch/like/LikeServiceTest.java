package com.example.lawSearch.like;

import com.example.lawSearch.domain.like.exception.AlreadyLikeException;
import com.example.lawSearch.domain.like.exception.LikeNotFoundException;
import com.example.lawSearch.domain.like.exception.SelfLikeException;
import com.example.lawSearch.domain.like.service.LikeService;
import com.example.lawSearch.domain.question.exception.QuestionNotFoundException;
import com.example.lawSearch.domain.suggestion.dto.request.CreateSuggestionDto;
import com.example.lawSearch.domain.suggestion.model.Suggestion;
import com.example.lawSearch.domain.suggestion.service.SuggestionService;
import com.example.lawSearch.domain.user.dto.request.UserRequestDto;
import com.example.lawSearch.domain.user.model.User;
import com.example.lawSearch.domain.user.service.UserService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class LikeServiceTest {
    @Autowired
    private SuggestionService suggestionService;

    @Autowired
    private UserService userService;

    @Autowired
    private LikeService likeService;

    @Test
    @Transactional
    @DisplayName("좋아요 기능")
    void like() {
        UserRequestDto userDto = new UserRequestDto("abcd@naver.com", "password", "kim", 20, true);
        userService.join(userDto);
        User user = userService.findByEmail(userDto.getEmail());
        CreateSuggestionDto suggestionDto = new CreateSuggestionDto("title", "content", "교육위원회");
        Long suggestionId = suggestionService.createSuggestion(suggestionDto, user);

        Suggestion suggestion = suggestionService.findById(suggestionId);

        UserRequestDto userDto2 = new UserRequestDto("abcd1@naver.com", "password2", "lee", 20, true);
        userService.join(userDto2);
        User user2 = userService.findByEmail(userDto2.getEmail());
        likeService.like(user2, suggestion.getId());

        UserRequestDto userDto3 = new UserRequestDto("abcd2@naver.com", "password2", "lee", 20, true);
        userService.join(userDto3);
        User user3 = userService.findByEmail(userDto3.getEmail());
        likeService.like(user3, suggestion.getId());

        UserRequestDto userDto4 = new UserRequestDto("abcd3@naver.com", "password2", "lee", 20, true);
        userService.join(userDto4);
        User user4 = userService.findByEmail(userDto4.getEmail());
        likeService.like(user4, suggestion.getId());

        Assertions.assertEquals(suggestion.getLikeList().size(), 3);
    }

    @Test
    @Transactional
    @DisplayName("좋아요를 누른 정책글에는 다시 좋아요를 할 수 없다.")
    void validationLike() {
        UserRequestDto userDto = new UserRequestDto("abcd@naver.com", "password", "kim", 20, true);
        userService.join(userDto);
        User user = userService.findByEmail(userDto.getEmail());
        CreateSuggestionDto suggestionDto = new CreateSuggestionDto("title", "content", "교육위원회");
        Long suggestionId = suggestionService.createSuggestion(suggestionDto, user);

        Suggestion suggestion = suggestionService.findById(suggestionId);

        UserRequestDto userDto2 = new UserRequestDto("abcd1@naver.com", "password2", "lee", 20, true);
        userService.join(userDto2);
        User user2 = userService.findByEmail(userDto2.getEmail());
        likeService.like(user2, suggestion.getId());

        assertThrows(AlreadyLikeException.class, () -> likeService.like(user2, suggestion.getId()), "");
        // 이미 좋아요를 누른 정책 건의는 좋아요를 할 수 없다.

        assertThrows(SelfLikeException.class, () -> likeService.like(user, suggestion.getId()), "");
        // 자신이 작성한 정책 건의에는 좋아요를 누를 수 없다.
    }

    @Test
    @DisplayName("좋아요 취소")
    void deleteLike() {
        UserRequestDto userDto = new UserRequestDto("abcd@naver.com", "password", "kim", 20, true);
        userService.join(userDto);
        User user = userService.findByEmail(userDto.getEmail());
        CreateSuggestionDto suggestionDto = new CreateSuggestionDto("title", "content", "교육위원회");
        Long suggestionId = suggestionService.createSuggestion(suggestionDto, user);

        Suggestion suggestion = suggestionService.findById(suggestionId);

        UserRequestDto userDto2 = new UserRequestDto("abcd1@naver.com", "password2", "lee", 20, true);
        userService.join(userDto2);
        User user2 = userService.findByEmail(userDto2.getEmail());

        likeService.like(user2, suggestion.getId());

        likeService.deleteLike(user2, suggestion.getId());

        assertThrows(LikeNotFoundException.class, () -> likeService.deleteLike(user2, suggestion.getId()), "");
        // 이미 삭제되어서 NotFoundException 발생
    }
}
