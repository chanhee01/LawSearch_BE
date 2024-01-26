package com.example.lawSearch.domain.answer.model;

import com.example.lawSearch.domain.question.model.Question;
import com.example.lawSearch.domain.user.model.User;
import com.example.lawSearch.global.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.*;
import static jakarta.persistence.GenerationType.*;

@Entity
@Getter
@NoArgsConstructor
public class Answer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String content;

    @OneToOne(fetch = LAZY)
    private Question question;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Answer(String content, Question question, User user) {
        this.content = content;
        this.question = question;
        this.user = user;
    }
}
