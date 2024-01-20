package com.example.lawSearch.domain.user.model;

import com.example.lawSearch.domain.answer.model.Answer;
import com.example.lawSearch.domain.like.model.Like;
import com.example.lawSearch.domain.question.model.Question;
import com.example.lawSearch.domain.suggestion.model.Suggestion;
import com.example.lawSearch.global.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static jakarta.persistence.CascadeType.*;
import static jakarta.persistence.GenerationType.*;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String email; // 이메일로 로그인

    private String password;

    private String name;

    private Integer age;

    private boolean sex; // true가 남자, false가 여자

    private String roles;

    @OneToMany(mappedBy = "user", cascade = ALL)
    private List<Question> questions;

    @OneToMany(mappedBy = "user", cascade = ALL)
    private List<Answer> answers;

    @OneToMany(mappedBy = "user", cascade = ALL)
    private List<Suggestion> suggestions;

    @OneToMany(mappedBy = "user", cascade = ALL)
    private List<Like> likeList;

    @Builder
    public User(String email, String password, String name, Integer age, boolean sex, String roles) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.roles = roles;
    }

    public List<String> getRoleList() {
        if (this.roles.length() > 0) {
            return Arrays.asList(this.roles.split(","));
        }
        return new ArrayList<>();
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public void addAnswer(Answer answer) {
        answers.add(answer);
    }

    public void addSuggestion(Suggestion suggestion) {
        suggestions.add(suggestion);
    }

    public void addLike(Like like) {
        likeList.add(like);
    }
}
