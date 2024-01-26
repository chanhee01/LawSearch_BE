package com.example.lawSearch.domain.question.repository;

import com.example.lawSearch.domain.question.model.Question;
import com.example.lawSearch.domain.user.model.User;
import com.example.lawSearch.global.base.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findAllByUser(User user);
    List<Question> findAllByCategory(Category category);
}
