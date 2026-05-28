package io.eddie.restapi.dto;

/**
 * packageName    : io.eddie.restapi.dto
 * fileName       : SavePostRequest
 * author         : Admin
 * date           : 26. 5. 28.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 5. 28.        Admin       최초 생성
 */
public record SavePostRequest(
        String title,
        String contents,
        String author
) {
}

/*

    {
        "title": "...",
        "contents": "...",
        "author": "..."
    }

 */