package com.example.lawSearch.global.auth.token.exception;

import com.example.lawSearch.global.exception.ErrorCode;
import com.example.lawSearch.global.exception.ServiceException;

public class AccessTokenExpiredException extends ServiceException {

    private static final ErrorCode ERROR_CODE = ErrorCode.ACCESS_TOKEN_EXPIRED;
    private static final String MESSAGE_KEY = "access 토큰이 만료";

    public AccessTokenExpiredException(String message) {
        super(ERROR_CODE, MESSAGE_KEY, new Object[]{message});
    }
}
