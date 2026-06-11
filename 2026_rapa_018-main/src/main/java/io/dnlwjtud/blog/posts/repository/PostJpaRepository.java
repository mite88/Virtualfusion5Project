package io.dnlwjtud.blog.posts.repository;

import io.dnlwjtud.blog.posts.entity.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostJpaRepository extends JpaRepository<Posts, Long> {
}
