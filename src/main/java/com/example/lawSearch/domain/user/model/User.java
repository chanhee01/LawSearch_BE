package com.example.lawSearch.domain.user.model;

import com.example.lawSearch.domain.answer.model.Answer;
import com.example.lawSearch.domain.question.model.Question;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

import static jakarta.persistence.GenerationType.*;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String password;

    private String name;

    private Integer age;

    private boolean sex;

    private String email;

    @OneToMany(mappedBy = "user")
    private List<Question> questions;

    @OneToMany(mappedBy = "user")
    private List<Answer> answers;

    @Builder
    public User(String password, String name, Integer age, boolean sex, String email) {
        this.password = password;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.email = email;
    }
}
