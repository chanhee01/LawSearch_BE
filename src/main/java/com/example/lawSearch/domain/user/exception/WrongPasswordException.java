package com.example.lawSearch.domain.user.exception;

import com.example.lawSearch.global.exception.ErrorCode;
import com.example.lawSearch.global.exception.ServiceException;

public class WrongPasswordException extends ServiceException {

    private static final ErrorCode ERROR_CODE = ErrorCode.WRONG_PASSWORD;
    private static final String MESSAGE_KEY = "비밀번호가 일치하지 않습니다.";

    public WrongPasswordException(String password) {
        super(ERROR_CODE, MESSAGE_KEY, new Object[] {password});
    }
}
