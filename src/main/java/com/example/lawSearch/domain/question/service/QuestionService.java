package com.example.lawSearch.domain.question.service;

import com.example.lawSearch.domain.question.dto.request.CreateQuestionRequest;
import com.example.lawSearch.domain.question.dto.request.UpdateQuestionRequest;
import com.example.lawSearch.domain.question.exception.QuestionNotFoundException;
import com.example.lawSearch.domain.question.exception.QuestionUserMismatchException;
import com.example.lawSearch.domain.question.model.Question;
import com.example.lawSearch.domain.question.repository.QuestionRepository;
import com.example.lawSearch.domain.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuestionService {

    private final QuestionRepository questionRepository;

    @Transactional
    public Long createQuestion(CreateQuestionRequest request, User user) {
        Question question = Question.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .category(request.getCategory())
                .user(user).build();

        Question save = questionRepository.save(question);
        return save.getId();
    }

    
}
