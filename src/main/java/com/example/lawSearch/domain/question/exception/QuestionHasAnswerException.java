package com.example.lawSearch.domain.question.exception;

import com.example.lawSearch.global.exception.ErrorCode;
import com.example.lawSearch.global.exception.ServiceException;

public class QuestionHasAnswerException extends ServiceException {
    private static final ErrorCode ERROR_CODE = ErrorCode.QUESTION_HAS_ANSWER;
    private static final String MESSAGE_KEY = "답변이 있는 질문은 삭제할 수 없습니다.";

    public QuestionHasAnswerException(Long questionId) {
        super(ERROR_CODE, MESSAGE_KEY, new Object[] {questionId});
    }
}
