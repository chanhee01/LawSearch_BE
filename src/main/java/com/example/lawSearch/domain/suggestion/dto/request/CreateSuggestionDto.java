package com.example.lawSearch.domain.suggestion.dto.request;

import com.example.lawSearch.global.base.category.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateSuggestionDto {

    private String title;
    private String content;
    private String category;

    public Category getCategory() {
        return Category.categoryConverter(category);
    }
}
