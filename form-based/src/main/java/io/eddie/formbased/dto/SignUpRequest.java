package io.eddie.formbased.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * packageName    : io.eddie.formbased.dto
 * fileName       : SignUpRequest
 * author         : Admin
 * date           : 26. 5. 21.
 * description    : Sign을 받을수 있는 dto(json)
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 5. 21.        Admin       최초 생성
 */

// Sign을 받을수 있는 dto(json)
@Getter
@AllArgsConstructor
public class SignUpRequest {

    private String username;
    private String password;
    private String email;
}
