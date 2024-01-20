package com.example.lawSearch.global.base.category;

import com.example.lawSearch.global.exception.ErrorCode;
import com.example.lawSearch.global.exception.ServiceException;

public class CategoryNotFoundException extends ServiceException {

    private static final ErrorCode ERROR_CODE = ErrorCode.CATEGORY_NOT_FOUND;
    private static final String MESSAGE_KEY = "해당 카테고리가 없습니다.";

    public CategoryNotFoundException(String category) {
        super(ERROR_CODE, MESSAGE_KEY, new Object[] {category});
    }
}
