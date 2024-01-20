package com.example.lawSearch.domain.question.dto.response;

import com.example.lawSearch.domain.answer.model.Answer;
import com.example.lawSearch.domain.question.model.Question;
import com.example.lawSearch.global.base.category.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionResponse {
    private String title;
    private String content;
    private Category category;
    private String comment;

    public QuestionResponse(Question question, Answer answer) {
        this.title = question.getTitle();
        this.content = question.getContent();
        this.category = question.getCategory();
        this.comment = answer.getContent();
    }

    public QuestionResponse(Question question) {
        this.title = question.getTitle();
        this.content = question.getContent();
        this.category = question.getCategory();
    }
}
