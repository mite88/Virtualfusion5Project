package io.eddie.jwt.dao;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * packageName    : io.eddie.jwt.dao
 * fileName       : Role
 * author         : Admin
 * date           : 26. 5. 22.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 5. 22.        Admin       최초 생성
 */
@Getter
@RequiredArgsConstructor
public enum Role {

    ADMIN("ADMIN"),
    MEMBER("MEMBER");


    private final String value;

    Role() {
        this.value = this.name();
    }
    public String getValue() {
        return value;
    }
}
