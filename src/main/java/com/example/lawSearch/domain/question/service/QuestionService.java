package com.example.lawSearch.domain.question.service;

import com.example.lawSearch.domain.question.dto.request.CreateQuestionRequest;
import com.example.lawSearch.domain.question.dto.request.UpdateQuestionRequest;
import com.example.lawSearch.domain.question.dto.response.QuestionListResponse;
import com.example.lawSearch.domain.question.exception.QuestionHasAnswerException;
import com.example.lawSearch.domain.question.exception.QuestionNotFoundException;
import com.example.lawSearch.domain.question.exception.QuestionUserMismatchException;
import com.example.lawSearch.domain.question.model.Question;
import com.example.lawSearch.domain.question.repository.QuestionRepository;
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

        if (question.getUser().getId() != user.getId()) {
            throw new QuestionUserMismatchException(user.getId());
        }

        if (question.getAnswer() != null) {
            throw new QuestionHasAnswerException(questionId);
        }

        question.updateQuestion(request.getTitle(), request.getContent());
        return question.getId();
    }

    @Transactional
    public void deleteQuestion(Long questionId, User user) {
        Question question = findById(questionId);

        if (question.getUser().getId() != user.getId()) {
            throw new QuestionUserMismatchException(user.getId());
        }

        questionRepository.delete(question);
    }

    public Question findById(Long questionId) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new QuestionNotFoundException(questionId));
        return question;
    }

    public List<QuestionListResponse> findAllByUser(User user) {
        List<Question> questionList = questionRepository.findAllByUser(user);
        List<QuestionListResponse> questions = questionList.stream().map(
                (question) -> QuestionListResponse.convert(question))
                .collect(Collectors.toList());
        return questions;
    }

    public List<QuestionListResponse> findByCategory(User user) {
        Category category = Category.categoryConverter(user.getName());
        List<Question> questionList = questionRepository.findAllByCategory(category);
        List<QuestionListResponse> questions = questionList.stream().map(
                        (question) -> QuestionListResponse.convert(question))
                .collect(Collectors.toList());
        return questions;
    }
}
