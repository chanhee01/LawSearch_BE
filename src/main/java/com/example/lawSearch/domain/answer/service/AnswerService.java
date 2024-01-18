package com.example.lawSearch.domain.answer.service;

import com.example.lawSearch.domain.answer.model.Answer;
import com.example.lawSearch.domain.answer.repository.AnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AnswerService {

    private final AnswerRepository answerRepository;

    public Answer findByQuestionId(Long questionId) {
        return answerRepository.findByQuestionId(questionId);
    }
}
