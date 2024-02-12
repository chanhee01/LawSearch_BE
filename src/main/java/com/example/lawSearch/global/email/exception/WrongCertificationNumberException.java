package com.example.lawSearch.global.email.exception;

import com.example.lawSearch.global.exception.ErrorCode;
import com.example.lawSearch.global.exception.ServiceException;

public class WrongCertificationNumberException extends ServiceException {

    private static final ErrorCode ERROR_CODE = ErrorCode.WRONG_CERTIFICATION_NUMBER;
    private static final String MESSAGE_KEY = "이메일 인증 번호가 틀렸습니다.";

    public WrongCertificationNumberException(Long certificationId) {
        super(ERROR_CODE, MESSAGE_KEY, new Object[] {certificationId});
    }
}
