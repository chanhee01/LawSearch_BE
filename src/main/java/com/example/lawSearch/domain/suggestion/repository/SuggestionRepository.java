package com.example.lawSearch.domain.suggestion.repository;

import com.example.lawSearch.domain.suggestion.model.Suggestion;
import com.example.lawSearch.domain.user.model.User;
import com.example.lawSearch.global.base.category.Category;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SuggestionRepository extends JpaRepository<Suggestion, Long>, SuggestionRepositoryCustom {

    @Query("select s from Suggestion s join fetch s.likeList l join fetch s.user u where s.id = :id")
    Optional<Suggestion> findById(Long id);

    @Query("select s from Suggestion s join fetch s.likeList l join s.user u where u = :user")
    List<Suggestion> findAllByUser(User user);

    List<Suggestion> findAllByCategory(Category category);
}
