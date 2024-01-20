package com.example.lawSearch.domain.question.model;

import com.example.lawSearch.domain.answer.model.Answer;
import com.example.lawSearch.domain.like.model.Like;
import com.example.lawSearch.domain.user.model.User;
import com.example.lawSearch.global.base.BaseEntity;
import com.example.lawSearch.global.base.category.Category;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.EnumType.*;
import static jakarta.persistence.FetchType.*;
import static jakarta.persistence.GenerationType.*;

@Entity
@Getter
@NoArgsConstructor
public class Question extends BaseEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String title;

    private String content;

    @Enumerated(STRING)
    private Category category;

    private boolean status; // 문의 중, 답변 완료

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(mappedBy = "question", cascade = ALL)
    private Answer answer;

    @Builder
    public Question(String title, String content, Category category, User user) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.status = false;
        this.user = user;
    }

    public void updateQuestion(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
