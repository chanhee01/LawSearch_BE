package com.example.lawSearch.domain.question.model;

import com.example.lawSearch.domain.answer.model.Answer;
import com.example.lawSearch.domain.user.model.User;
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
public class Question {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String content;

    private String category; // 위원회 설정해주기

    private boolean status; // 문의 중, 답변 완료

    @ManyToOne(fetch = LAZY, cascade = ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(mappedBy = "question")
    private Answer answer;

    @Builder
    public Question(String content, String category, User user, Answer answer) {
        this.content = content;
        this.category = category;
        this.status = false;
        this.user = user;
        this.answer = answer;
    }
}
