package io.dnlwjtud.blog.blog.global.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ResponseCode {

    // Success
    SUCCESS(HttpStatus.OK, "S000", "성공"),

    // Member
    DUPLICATE_USERNAME(HttpStatus.CONFLICT, "M001", "이미 사용 중인 아이디입니다."),
    DUPLICATE_EMAIL(HttpStatus.CONFLICT, "M002", "이미 사용 중인 이메일입니다."),
    DUPLICATE_MEMBER(HttpStatus.CONFLICT, "M003", "이미 존재하는 회원 정보입니다."),

    // Auth
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "A001", "비밀번호가 일치하지 않습니다."),
    USER_NOT_FOUND(HttpStatus.UNAUTHORIZED, "A002", "존재하지 않는 사용자입니다."),
    INVALID_REFRESH_TOKEN(HttpStatus.UNAUTHORIZED, "A003", "유효하지 않은 RefreshToken입니다."),
    REFRESH_TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "A004", "만료되었거나 로그아웃된 RefreshToken입니다."),
    REFRESH_TOKEN_MISMATCH(HttpStatus.UNAUTHORIZED, "A005", "제공된 RefreshToken이 현재 유효한 RefreshToken과 일치하지 않습니다."),

    // Common
    INPUT_REQUIRED(HttpStatus.BAD_REQUEST, "C001", "입력값이 필요합니다."),

    // AI Job
    JOB_NOT_FOUND(HttpStatus.NOT_FOUND, "J001", "작업을 찾을 수 없습니다."),

    // Post
    POST_NOT_FOUND(HttpStatus.NOT_FOUND, "P002", "게시글을 찾을 수 없습니다."), // 추가
    UNAUTHORIZED_POST_UPDATE(HttpStatus.FORBIDDEN, "P001", "본인 글이 아니면 수정할 수 없습니다."),

    // Server
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "E001", "서버 내부 오류가 발생했습니다."),
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}