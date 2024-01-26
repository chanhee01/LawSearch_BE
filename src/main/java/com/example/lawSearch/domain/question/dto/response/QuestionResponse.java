package com.example.lawSearch.domain.question.dto.response;

import com.example.lawSearch.domain.answer.model.Answer;
import com.example.lawSearch.domain.question.model.Question;
import com.example.lawSearch.global.base.category.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionResponse {
    private String title;
    private String content;
    private Category category;
    private boolean status;
    private LocalDateTime createdTime;
    private String comment;
    private LocalDateTime commentTime;

    public QuestionResponse(Question question, Answer answer) {
        this.title = question.getTitle();
        this.content = question.getContent();
        this.category = question.getCategory();
        this.status = question.isStatus();
        this.createdTime = question.getCreatedDate();
        this.comment = answer.getContent();
        this.commentTime = answer.getCreatedDate();
    }

    public QuestionResponse(Question question) {
        this.title = question.getTitle();
        this.content = question.getContent();
        this.category = question.getCategory();
        this.status = question.isStatus();
        this.createdTime = question.getCreatedDate();
    }
}
