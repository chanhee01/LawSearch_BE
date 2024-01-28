package com.example.lawSearch.domain.answer.service;

import com.example.lawSearch.domain.answer.exception.AnswerAlreadyExistException;
import com.example.lawSearch.domain.answer.model.Answer;
import com.example.lawSearch.domain.answer.repository.AnswerRepository;
import com.example.lawSearch.domain.question.model.Question;
import com.example.lawSearch.domain.question.service.QuestionService;
import com.example.lawSearch.domain.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AnswerService {

    private final AnswerRepository answerRepository;
    private final QuestionService questionService;

    public Answer findByQuestionId(Long questionId) {
        Answer answer = answerRepository.findByQuestionId(questionId);
        if (answer == null) {
            return null;
        }
        return answer;
    }

    @Transactional
    public Long save(User user, Long questionId, String content) {
        Question question = questionService.findById(questionId);

        if (answerRepository.findByQuestionId(question.getId()) != null) {
            throw new AnswerAlreadyExistException(questionId);
        }

        Answer answer = Answer.builder()
                .question(question)
                .content(content)
                .user(user).build();

        answerRepository.save(answer);
        question.updateStatus();
        return answer.getId();
    }
}
