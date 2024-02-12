package com.example.lawSearch.global.email.exception;

import com.example.lawSearch.global.exception.ErrorCode;
import com.example.lawSearch.global.exception.ServiceException;

public class CertificationExpiredException extends ServiceException {

    private static final ErrorCode ERROR_CODE = ErrorCode.CERTIFICATION_EXPIRED;
    private static final String MESSAGE_KEY = "인증 시간이 만료되었습니다.";

    public CertificationExpiredException(Long certificationId) {
        super(ERROR_CODE, MESSAGE_KEY, new Object[] {certificationId});
    }
}
