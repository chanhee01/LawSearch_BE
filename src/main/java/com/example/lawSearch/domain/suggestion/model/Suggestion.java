package com.example.lawSearch.domain.suggestion.model;

import com.example.lawSearch.domain.like.model.Like;
import com.example.lawSearch.domain.user.model.User;
import com.example.lawSearch.global.base.BaseEntity;
import com.example.lawSearch.global.base.category.Category;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
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
    private Long id;

    private String title;

    private String content;

    @Enumerated(STRING)
    private Category category;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "suggestion", cascade = ALL)
    private List<Like> likeList = new ArrayList<>();

    private Long likeCount;

    @Builder
    public Suggestion(String title, String content, Category category, User user) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.user = user;
    }

    public void addLike(Like like) {
        likeList.add(like);
        likeCount++;
    }

    public void deleteLike(Like like) {
        likeList.remove(like);
        likeCount--;
    }
}
