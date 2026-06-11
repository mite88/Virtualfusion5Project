package io.dnlwjtud.blog.posts.mapper;

import io.dnlwjtud.blog.posts.dto.PostDescription;
import io.dnlwjtud.blog.posts.entity.Posts;

public class PostMapper {

    public static PostDescription toDescription(Posts entity) {
        return new PostDescription(
                entity.getTitle(),
                entity.getContent(),
                entity.getAuthor().getUsername(),
                entity.getCreatedAt()
        );
    }

}
