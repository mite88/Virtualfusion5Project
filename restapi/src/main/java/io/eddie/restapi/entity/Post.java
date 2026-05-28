package io.eddie.restapi.entity;

import io.eddie.restapi.dto.SavePostRequest;import io.eddie.restapi.dto.UpdatePostRequest;import lombok.Data;

import java.time.LocalDateTime;

/**
 * packageName    : io.eddie.restapi.entity
 * fileName       : Post
 * author         : Admin
 * date           : 26. 5. 28.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 5. 28.        Admin       최초 생성
 */
@Data
public class Post {

    private Long id;

    private String title;
    private String contents;

    private String author;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static Post of(SavePostRequest request) {
        Post post = new Post();

        post.setTitle(request.title());
        post.setContents(request.contents());
        post.setAuthor(request.author());

        return post;
    }

    public void update(UpdatePostRequest request) {

        this.title = request.title();
        this.contents = request.contents();

        this.updatedAt = LocalDateTime.now();

    }
}
