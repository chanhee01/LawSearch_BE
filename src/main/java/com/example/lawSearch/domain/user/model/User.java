package com.example.lawSearch.domain.user.model;

import com.example.lawSearch.domain.answer.model.Answer;
import com.example.lawSearch.domain.question.model.Question;
import com.example.lawSearch.global.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    @OneToMany(mappedBy = "user")
    private List<Question> questions;

    @OneToMany(mappedBy = "user")
    private List<Answer> answers;

    @Builder
    public User(String password, String name, Integer age, boolean sex, String email, String roles) {
        this.password = password;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.email = email;
        this.roles = roles;
    }

    public List<String> getRoleList() {
        if (this.roles.length() > 0) {
            return Arrays.asList(this.roles.split(","));
        }
        return new ArrayList<>();
    }
}
