package com.example.lawSearch.global.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {

    // login
    EMAIL_EXIST(HttpStatus.BAD_REQUEST, "Login01", "이메일이 이미 존재합니다."),
    USER_NOT_FOUND(HttpStatus.BAD_REQUEST, "Login02", "해당 사용자가 존재하지 않습니다."),

    // email
    CERTIFICATION_NOT_FOUND(HttpStatus.BAD_REQUEST, "email01", "해당 이메일 인증이 존재하지 않습니다."),
    CERTIFICATION_EXPIRED(HttpStatus.BAD_REQUEST, "email02", "인증 시간이 만료되었습니다."),
    WRONG_CERTIFICATION_NUMBER(HttpStatus.BAD_REQUEST, "email03", "이메일 인증번호가 틀렸습니다."),

    // user
    WRONG_PASSWORD(HttpStatus.BAD_REQUEST, "User01", "비밀번호가 일치하지 않습니다."),

    // token
    REFRESH_TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "Token01", "refresh 토큰이 만료되어 다시 로그인이 필요합니다."),
    ACCESS_TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "Token02", "access 토큰이 만료"),

    // question
    QUESTION_NOT_FOUND(HttpStatus.BAD_REQUEST, "Question01", "문의가 존재하지 않습니다."),
    QUESTION_USER_MIX_MATCH(HttpStatus.BAD_REQUEST, "Question02", "작성자와 사용자가 일치하지 않습니다."),
    QUESTION_HAS_ANSWER(HttpStatus.BAD_REQUEST, "Question02", "답변이 있는 질문은 삭제할 수 없습니다."),

    // suggestion
    SUGGESTION_NOT_FOUND(HttpStatus.BAD_REQUEST, "Suggestion01", "해당 정책 건의가 존재하지 않습니다."),
    SUGGESTION_USER_MIX_MATCH(HttpStatus.BAD_REQUEST, "Suggestion02", "작성자와 사용자가 일치하지 않습니다."),

    // category
    CATEGORY_NOT_FOUND(HttpStatus.BAD_REQUEST, "Category01", "해당 카테고리가 없습니다."),

    // like
    SELF_LIKE(HttpStatus.BAD_REQUEST, "Like01", "자신의 정책 건의에는 좋아요를 할 수 없습니다."),
    ALREADY_LIKE(HttpStatus.BAD_REQUEST, "Like02", "좋아요를 이미 누른 상태입니다."),
    LIKE_NOT_FOUND(HttpStatus.BAD_REQUEST, "Like03", "좋아요를 누르지 않았습니다."),

    // answer
    ANSWER_ALREADY_EXIST(HttpStatus.BAD_REQUEST, "answer01", "답변이 이미 존재합니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;

    ErrorCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
