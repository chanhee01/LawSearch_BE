package com.example.lawSearch.global.auth.token.exception;

import com.example.lawSearch.global.exception.ErrorCode;
import com.example.lawSearch.global.exception.ServiceException;

public class RefreshTokenExpiredException extends ServiceException {

    private static final ErrorCode ERROR_CODE = ErrorCode.REFRESH_TOKEN_EXPIRED;
    private static final String MESSAGE_KEY = "refresh 토큰이 만료되어 다시 로그인이 필요합니다.";

    public RefreshTokenExpiredException(String message) {
        super(ERROR_CODE, MESSAGE_KEY, new Object[]{message});
    }
}
