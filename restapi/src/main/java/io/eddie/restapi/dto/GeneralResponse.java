package io.eddie.restapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * packageName    : io.eddie.restapi.dto
 * fileName       : GeneralResponse
 * author         : Admin
 * date           : 26. 5. 28.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 5. 28.        Admin       최초 생성
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GeneralResponse<T> {
    private String message;
    private String code;
    private T data;
}
