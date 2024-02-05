package com.example.lawSearch.domain.user.exception;

import com.example.lawSearch.global.exception.ErrorCode;
import com.example.lawSearch.global.exception.ServiceException;

public class CertificationNotFoundException extends ServiceException {

    private static final ErrorCode ERROR_CODE = ErrorCode.CERTIFICATION_NOT_FOUND;
    private static final String MESSAGE_KEY = "해당 이메일 인증이 존재하지 않습니다.";

    public CertificationNotFoundException(Long certificationId) {
        super(ERROR_CODE, MESSAGE_KEY, new Object[] {certificationId});
    }
}
