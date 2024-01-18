package com.example.lawSearch.domain.answer.repository;

import com.example.lawSearch.domain.answer.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    Answer findByQuestionId(Long questionId);
}
