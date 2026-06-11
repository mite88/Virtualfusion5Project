package io.eddie.restapi.controller;

import io.eddie.restapi.dto.GeneralResponse;
import io.eddie.restapi.dto.SavePostRequest;
import io.eddie.restapi.dto.UpdatePostRequest;
import io.eddie.restapi.entity.Post;
import io.eddie.restapi.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostRepository repository;

    // 다건조회
    @GetMapping
    @ResponseStatus(HttpStatus.OK) // 200
    public GeneralResponse<List<Object>> getPosts() {

        List<Object> objects = List.of();

        return GeneralResponse.<List<Object>>builder()
                .message("성공적으로 목록을 조회했습니다.")
                .code("S-100")
                .data(objects)
                .build();

    }

    // 단건조회
    @GetMapping("/{postId}")
    @ResponseStatus(HttpStatus.OK) // 201
    public GeneralResponse<Post> getPost(
            @PathVariable Long postId
    ) {
        Post findPost = repository.findById(postId);

        return GeneralResponse.<Post>builder()
                .message("성공적으로 게시물을 조회했습니다.")
                .code("S-101")
                .data(findPost)
                .build();

    }

    // 게시물 생성
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // 201
    public GeneralResponse<Post> savePost(
            @RequestBody SavePostRequest request
    ) {
        log.info("Save post request: {}", request);
        Post findPost = repository.save(Post.of(request));
        return GeneralResponse.<Post>builder()
                .message("성공적으로 게시물을 생성했습니다.")
                .code("S-102")
                .data(findPost)
                .build();

    }

    // 특정 게시물 수정
    @PatchMapping("/{postId}")
    @ResponseStatus(HttpStatus.OK) // 200
    public GeneralResponse<Post> updatePost(
            @PathVariable Long postId,
            @RequestBody UpdatePostRequest request
    ) {
        Post updated = repository.update(postId, request);
        return GeneralResponse.<Post>builder()
                .message("성공적으로 게시물을 수정했습니다.")
                .code("S-103")
                .data(updated)
                .build();
    }

    // 특정 게시물 삭제
    @DeleteMapping("/{postId}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // 204
    public GeneralResponse<Void> deletePost(
            @PathVariable Long postId
    ) {
        repository.deleteById(postId);
        return GeneralResponse.<Void>builder()
                .message("성공적으로 게시물을 삭제했습니다.")
                .code("S-104")
                .build();
    }

}