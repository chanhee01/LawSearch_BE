package com.example.lawSearch.domain.question.model;

import com.example.lawSearch.domain.answer.model.Answer;
import com.example.lawSearch.domain.user.model.User;
import com.example.lawSearch.global.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.CascadeType.*;
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

    private String category; // 위원회 설정해주기

    private boolean status; // 문의 중, 답변 완료

    @ManyToOne(fetch = LAZY, cascade = ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(mappedBy = "question")
    private Answer answer;

    @Builder
    public Question(String title, String content, String category, User user) {
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
