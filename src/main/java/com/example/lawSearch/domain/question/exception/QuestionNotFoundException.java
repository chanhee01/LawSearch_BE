package com.example.lawSearch.domain.question.exception;

import com.example.lawSearch.global.exception.ErrorCode;
import com.example.lawSearch.global.exception.ServiceException;

public class QuestionNotFoundException extends ServiceException {

    private static final ErrorCode ERROR_CODE = ErrorCode.QUESTION_NOT_FOUND;
    private static final String MESSAGE_KEY = "문의가 존재하지 않습니다.";

    public QuestionNotFoundException(Long questionId) {
        super(ERROR_CODE, MESSAGE_KEY, new Object[] {questionId});
    }
}
