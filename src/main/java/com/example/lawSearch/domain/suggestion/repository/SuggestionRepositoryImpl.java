package com.example.lawSearch.domain.suggestion.repository;

import com.example.lawSearch.domain.suggestion.model.Suggestion;
import com.example.lawSearch.global.base.category.Category;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.example.lawSearch.domain.suggestion.model.QSuggestion.*;
import static com.example.lawSearch.domain.user.model.QUser.*;

public class SuggestionRepositoryImpl implements SuggestionRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public SuggestionRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<Suggestion> findAllSuggestion(Category category, Boolean likeCount, Pageable pageable) {
        JPAQuery<Suggestion> query = queryFactory
                .selectFrom(suggestion)
                .leftJoin(suggestion.user, user)
                .where(
                        categoryEq(category),
                        likeCountEq(likeCount)
                );

        List<Suggestion> content = query
                .orderBy(likeCount != null && likeCount ? suggestion.likeCount.desc() : suggestion.likeCount.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = query.fetchCount();

        return new PageImpl<>(content, pageable, total);
    }

    private BooleanExpression categoryEq(Category category) {
        return category != null ? suggestion.category.eq(category) : null;
    }

    private BooleanExpression likeCountEq(Boolean likeCount) {
        if (likeCount != null && likeCount) {
            return suggestion.likeList.isNotEmpty();
        }
        return null;
    }
}
