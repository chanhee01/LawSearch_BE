package com.example.lawSearch.domain.like.exception;

import com.example.lawSearch.global.exception.ErrorCode;
import com.example.lawSearch.global.exception.ServiceException;

public class AlreadyLikeException extends ServiceException {

    private static final ErrorCode ERROR_CODE = ErrorCode.ALREADY_LIKE;
    private static final String MESSAGE_KEY = "좋아요를 이미 누른 상태입니다.";

    public AlreadyLikeException(Long suggestionId) {
        super(ERROR_CODE, MESSAGE_KEY, new Object[] {suggestionId});
    }
}