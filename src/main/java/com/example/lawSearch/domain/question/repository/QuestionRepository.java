package com.example.lawSearch.domain.question.repository;

import com.example.lawSearch.domain.question.model.Question;
import com.example.lawSearch.domain.user.model.User;
import com.example.lawSearch.global.base.category.Category;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findAllByUser(User user);

    @Query("select q from Question q join fetch q.user u join fetch q.answer a where q.category = :category")
    List<Question> findAllByCategory(Category category);
}
