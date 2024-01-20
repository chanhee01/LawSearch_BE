package com.example.lawSearch.domain.like.model;

import com.example.lawSearch.domain.suggestion.model.Suggestion;
import com.example.lawSearch.domain.user.model.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Like {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "suggestion_id")
    private Suggestion suggestion;

    @Builder
    public Like(User user, Suggestion suggestion) {
        this.user = user;
        this.suggestion = suggestion;
    }
}
