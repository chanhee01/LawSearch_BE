package com.example.lawSearch.domain.like.exception;

import com.example.lawSearch.global.exception.ErrorCode;
import com.example.lawSearch.global.exception.ServiceException;

public class SelfLikeException extends ServiceException {

    private static final ErrorCode ERROR_CODE = ErrorCode.SELF_LIKE;
    private static final String MESSAGE_KEY = "자신의 정책 건의에는 좋아요를 할 수 없습니다.";

    public SelfLikeException(Long suggestionId) {
        super(ERROR_CODE, MESSAGE_KEY, new Object[] {suggestionId});
    }
}