package com.example.lawSearch.domain.question.exception;

import com.example.lawSearch.global.exception.ErrorCode;
import com.example.lawSearch.global.exception.ServiceException;

public class QuestionUserMismatchException extends ServiceException {

    private static final ErrorCode ERROR_CODE = ErrorCode.QUESTION_USER_MIX_MATCH;
    private static final String MESSAGE_KEY = "작성자와 사용자가 일치하지 않습니다.";

    public QuestionUserMismatchException(Long userId) {
        super(ERROR_CODE, MESSAGE_KEY, new Object[] {userId});
    }
}
