package com.example.lawSearch.domain.answer.exception;

import com.example.lawSearch.global.exception.ErrorCode;
import com.example.lawSearch.global.exception.ServiceException;

public class AnswerAlreadyExistException extends ServiceException {

    private static final ErrorCode ERROR_CODE = ErrorCode.ANSWER_ALREADY_EXIST;
    private static final String MESSAGE_KEY = "답변이 이미 존재합니다.";

    public AnswerAlreadyExistException(Long questionId) {
        super(ERROR_CODE, MESSAGE_KEY, new Object[] {questionId});
    }
}
