package com.example.lawSearch.suggestion;

import com.example.lawSearch.domain.question.exception.QuestionNotFoundException;
import com.example.lawSearch.domain.suggestion.dto.request.CreateSuggestionDto;
import com.example.lawSearch.domain.suggestion.model.Suggestion;
import com.example.lawSearch.domain.suggestion.service.SuggestionService;
import com.example.lawSearch.domain.user.dto.request.UserRequestDto;
import com.example.lawSearch.domain.user.model.User;
import com.example.lawSearch.domain.user.service.UserService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
}
