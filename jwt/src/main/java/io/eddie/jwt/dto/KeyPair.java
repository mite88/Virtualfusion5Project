package io.eddie.jwt.dto;

import lombok.Builder;
import lombok.Getter;

/**
 * packageName    : io.eddie.jwt.dto
 * fileName       : KeyPair
 * author         : Admin
 * date           : 26. 5. 22.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 5. 22.        Admin       최초 생성
 */
@Getter
@Builder
public class KeyPair {
    private String accessToken;
    private String refreshToken;
}