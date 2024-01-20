package com.example.lawSearch.domain.suggestion.model;

import com.example.lawSearch.domain.like.model.Like;
import com.example.lawSearch.domain.user.model.User;
import com.example.lawSearch.global.base.BaseEntity;
import com.example.lawSearch.global.base.category.Category;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.EnumType.*;
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

    @Enumerated(STRING)
    private Category category; // 위원회 enum으로 설정

    private Integer likeCount;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "suggestion", cascade = ALL)
    private List<Like> likeList;

    @Builder
    public Suggestion(String title, String content, Category category, User user) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.likeCount = 0;
        this.user = user;
    }

    public void addLike(Like like) {
        likeList.add(like);
    }
}
