package com.example.lawSearch.domain.suggestion.repository;

import com.example.lawSearch.domain.suggestion.model.Suggestion;
import com.example.lawSearch.global.base.category.Category;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;

import java.util.List;

import static com.example.lawSearch.domain.suggestion.model.QSuggestion.*;

public class SuggestionRepositoryImpl implements SuggestionRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public SuggestionRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Suggestion> findAllSuggestion(Category category, Boolean likeCount) {
        JPAQuery<Suggestion> query = queryFactory.selectFrom(suggestion);

        if(category != null) {
            query.where(suggestion.category.eq(category));
        }

        if (likeCount != null && likeCount) {
            query.orderBy(suggestion.likeCount.desc());
        } else {
            query.orderBy(suggestion.createdDate.desc());
        }
        return query.fetch();
    }
}
