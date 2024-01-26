package com.example.lawSearch.domain.like.exception;

import com.example.lawSearch.global.exception.ErrorCode;
import com.example.lawSearch.global.exception.ServiceException;

public class LikeNotFoundException extends ServiceException {

    private static final ErrorCode ERROR_CODE = ErrorCode.LIKE_NOT_FOUND;
    private static final String MESSAGE_KEY = "좋아요를 누르지 않았습니다.";

    public LikeNotFoundException(Long suggestionId) {
        super(ERROR_CODE, MESSAGE_KEY, new Object[] {suggestionId});
    }
}