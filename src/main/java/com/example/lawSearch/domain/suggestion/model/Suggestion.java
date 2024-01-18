package com.example.lawSearch.domain.suggestion.model;

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
public class Suggestion extends BaseEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long Id;

    private String title;

    private String content;

    private String category; // 위원회 enum으로 설정

    private Integer likeCount;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Suggestion(String title, String content, String category) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.likeCount = 0;
    }
}
