package com.example.lawSearch.domain.user.exception;

import com.example.lawSearch.global.exception.ErrorCode;
import com.example.lawSearch.global.exception.ServiceException;

public class UserNotFoundException extends ServiceException {

    private static final ErrorCode ERROR_CODE = ErrorCode.USER_NOT_FOUND;
    private static final String MESSAGE_KEY = "해당 사용자가 존재하지 않습니다.";

    public UserNotFoundException(Long userId) {
        super(ERROR_CODE, MESSAGE_KEY, new Object[] {userId});
    }
}
