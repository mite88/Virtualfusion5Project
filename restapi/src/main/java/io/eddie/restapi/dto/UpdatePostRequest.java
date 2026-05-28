package io.eddie.restapi.dto;

/**
 * packageName    : io.eddie.restapi.dto
 * fileName       : recode
 * author         : Admin
 * date           : 26. 5. 28.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 5. 28.        Admin       최초 생성
 */
public record UpdatePostRequest(
        String title,
        String contents
) {

}

/*

    {
        "title": "...",
        "contents": "..."
    }

 */
