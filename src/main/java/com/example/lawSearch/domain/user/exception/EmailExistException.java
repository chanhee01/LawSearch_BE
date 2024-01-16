package com.example.lawSearch.domain.user.exception;

import com.example.lawSearch.global.exception.ErrorCode;
import com.example.lawSearch.global.exception.ServiceException;

public class EmailExistException extends ServiceException {

    private static final ErrorCode ERROR_CODE = ErrorCode.EMAIL_EXIST;
    private static final String MESSAGE_KEY = "이메일이 이미 존재합니다.";

    public EmailExistException(String email) {
        super(ERROR_CODE, MESSAGE_KEY, new Object[] {email});
    }
}
