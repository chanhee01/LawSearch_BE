package com.example.lawSearch.domain.user.service;

import com.example.lawSearch.domain.question.dto.response.QuestionListResponse;
import com.example.lawSearch.domain.question.service.QuestionService;
import com.example.lawSearch.domain.suggestion.dto.response.SuggestionListResponse;
import com.example.lawSearch.domain.suggestion.service.SuggestionService;
import com.example.lawSearch.domain.user.dto.request.UserRequestDto;
import com.example.lawSearch.domain.user.dto.response.MyPageResponseDto;
import com.example.lawSearch.domain.user.dto.response.UserResponseDto;
import com.example.lawSearch.domain.user.exception.EmailExistException;
import com.example.lawSearch.domain.user.exception.UserNotFoundException;
import com.example.lawSearch.domain.user.model.User;
import com.example.lawSearch.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;
    private final QuestionService questionService;
    private final SuggestionService suggestionService;

    @Transactional
    public UserResponseDto join(UserRequestDto request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailExistException(request.getEmail());
        }
        User user = User.builder()
                .email(request.getEmail())
                .password(bCryptPasswordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .age(request.getAge())
                .sex(request.isSex())
                .roles("ROLE_USER").build();

        userRepository.save(user);

        return new UserResponseDto(user.getName());
    }

    public MyPageResponseDto myPage(User user) {
        List<QuestionListResponse> questionList = questionService.findAllByUser(user);
        List<SuggestionListResponse> suggestionList = suggestionService.findAllByUser(user);

        Integer questions = (questionList == null) ? 0 : questionList.size();
        Integer suggestions = (suggestionList == null) ? 0 : suggestionList.size();

        return new MyPageResponseDto(questions, suggestions);
    }

    public boolean validationEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public User findByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return user;
    }

    public User findById(Long id) {
        User user = userRepository.findById(id).
                orElseThrow(() -> new UserNotFoundException(id));
        return user;
    }

}
