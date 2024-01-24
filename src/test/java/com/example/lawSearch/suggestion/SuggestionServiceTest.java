package com.example.lawSearch.suggestion;

import com.example.lawSearch.domain.question.exception.QuestionNotFoundException;
import com.example.lawSearch.domain.suggestion.dto.request.CreateSuggestionDto;
import com.example.lawSearch.domain.suggestion.dto.response.SuggestionListResponse;
import com.example.lawSearch.domain.suggestion.model.Suggestion;
import com.example.lawSearch.domain.suggestion.service.SuggestionService;
import com.example.lawSearch.domain.user.dto.request.UserRequestDto;
import com.example.lawSearch.domain.user.model.User;
import com.example.lawSearch.domain.user.service.UserService;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class SuggestionServiceTest {

    @Autowired
    private SuggestionService suggestionService;

    @Autowired
    private UserService userService;

    @Test
    @Transactional
    @DisplayName("정책 건의 작성")
    void createSuggestion() {
        UserRequestDto userDto = new UserRequestDto("email@naver.com", "password", "kim", 20, true);
        userService.join(userDto);
        User user = userService.findByEmail(userDto.getEmail());
        CreateSuggestionDto suggestionDto = new CreateSuggestionDto("title", "content", "교육위원회");
        Long suggestionId = suggestionService.createSuggestion(suggestionDto, user);

        Suggestion suggestion = suggestionService.findById(suggestionId);

        assertEquals(suggestion.getTitle(), suggestionDto.getTitle());
        assertEquals(suggestion.getContent(), suggestionDto.getContent());
        assertEquals(suggestion.getCategory(), suggestionDto.getCategory());
        assertEquals(suggestion.getUser(), user);
    }

    @Test
    @Transactional
    @DisplayName("내가 작성한 정책 건의 리스트")
    void mySuggestion() {
        UserRequestDto userDto1 = new UserRequestDto("email@naver.com", "password", "kim", 20, true);
        userService.join(userDto1);
        User user1 = userService.findByEmail(userDto1.getEmail());
        CreateSuggestionDto suggestionDto1 = new CreateSuggestionDto("title1", "content1", "교육위원회");
        Long suggestionId1 = suggestionService.createSuggestion(suggestionDto1, user1);

        CreateSuggestionDto suggestionDto2 = new CreateSuggestionDto("title2", "content2", "교육위원회");
        Long suggestionId2 = suggestionService.createSuggestion(suggestionDto2, user1);

        // 다른 사람이 작성한 정책 건의 글
        UserRequestDto userDto2 = new UserRequestDto("email2@naver.com", "password2", "lee", 30, false);
        userService.join(userDto2);
        User user2 = userService.findByEmail(userDto2.getEmail());
        CreateSuggestionDto suggestionDto3 = new CreateSuggestionDto("title3", "content3", "교육위원회");
        Long suggestionId3 = suggestionService.createSuggestion(suggestionDto3, user2);

        List<SuggestionListResponse> suggestions = suggestionService.findAllByUser(user1);

        List<Long> suggestionIds = suggestions.stream().map(suggestion -> suggestion.getId()).collect(Collectors.toList());

        assertThat(suggestionIds).contains(suggestionId1);
        assertThat(suggestionIds).contains(suggestionId2);
        assertThat(suggestionIds).doesNotContain(suggestionId3); // 다른 사람의 정책 건의는 포함되지 않는다.
    }

    @Test
    @Transactional
    @DisplayName("조건을 넣은 위원회의 정책 건의만 반환")
    void allSuggestion() {
        UserRequestDto userDto1 = new UserRequestDto("email@naver.com", "password", "kim", 20, true);
        userService.join(userDto1);
        User user1 = userService.findByEmail(userDto1.getEmail());
        CreateSuggestionDto suggestionDto1 = new CreateSuggestionDto("title1", "content1", "교육위원회");
        Long suggestionId1 = suggestionService.createSuggestion(suggestionDto1, user1);

        CreateSuggestionDto suggestionDto2 = new CreateSuggestionDto("title2", "content2", "정보위원회");
        Long suggestionId2 = suggestionService.createSuggestion(suggestionDto2, user1);

        // 다른 사람이 작성한 정책 건의 글
        UserRequestDto userDto2 = new UserRequestDto("email2@naver.com", "password2", "lee", 30, false);
        userService.join(userDto2);
        User user2 = userService.findByEmail(userDto2.getEmail());
        CreateSuggestionDto suggestionDto3 = new CreateSuggestionDto("title3", "content3", "교육위원회");
        Long suggestionId3 = suggestionService.createSuggestion(suggestionDto3, user2);

        List<SuggestionListResponse> suggestions = suggestionService.findAllSuggestion("교육위원회", true);

        List<Long> suggestionIds = suggestions.stream().map(suggestion -> suggestion.getId()).collect(Collectors.toList());

        assertThat(suggestionIds).contains(suggestionId1);
        assertThat(suggestionIds).contains(suggestionId3);
        assertThat(suggestionIds).doesNotContain(suggestionId2); // 다른 위원회는 포함 X
    }
}
