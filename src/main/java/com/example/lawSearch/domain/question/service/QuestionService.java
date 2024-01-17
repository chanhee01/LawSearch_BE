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

import java.util.Optional;


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

    @Transactional
    public Long updateQuestion(UpdateQuestionRequest request, Long questionId, User user) {
        Question question = findById(questionId);

        if (question.getUser() != user) {
            throw new QuestionUserMismatchException(user.getId());
        }

        question.updateQuestion(request.getTitle(), request.getContent());
        return question.getId();
    }

    public Question findById(Long questionId) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new QuestionNotFoundException(questionId));
        return question;
    }
}
