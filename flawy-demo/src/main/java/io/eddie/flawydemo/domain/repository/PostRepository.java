package io.eddie.flawydemo.domain.repository;

import io.eddie.flawydemo.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}