package com.example.lawSearch.domain.question.exception;

import com.example.lawSearch.global.exception.ErrorCode;
import com.example.lawSearch.global.exception.ServiceException;

public class QuestionUserMismatchException extends ServiceException {

    private static final ErrorCode ERROR_CODE = ErrorCode.QUESTION_USER_MIX_MATCH;
    private static final String MESSAGE_KEY = "문의글의 작성자와 다른 사용자입니다";

    public QuestionUserMismatchException(Long userId) {
        super(ERROR_CODE, MESSAGE_KEY, new Object[] {userId});
    }
}
