package io.eddie.restapi.controller;

import io.eddie.restapi.dto.GeneralResponse;
import io.eddie.restapi.dto.SavePostRequest;
import io.eddie.restapi.dto.UpdatePostRequest;
import io.eddie.restapi.entity.Post;
import io.eddie.restapi.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * packageName    : io.eddie.restapi.controller
 * fileName       : PostController
 * author         : Admin
 * date           : 26. 5. 28.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 5. 28.        Admin       최초 생성
 */
@RestController //Rest api용 컨트롤러
@RequestMapping("/posts")
@Slf4j
@RequiredArgsConstructor
public class PostController {

    private final PostRepository repository;

    //curd api endpoints

    //다건 조회
    @GetMapping()
    @ResponseStatus(HttpStatus.OK) //200
   /* public ResponseEntity<List<Object>> getPosts(){
        //없이니 그냥 만들어줌
        List<Object> objects=List.of();

        return ResponseEntity.ok(objects);
    }*/
    public GeneralResponse<List<Object>> getPosts(){
        //없이니 그냥 만들어줌
        List<Object> objects=List.of();

        return GeneralResponse.<List<Object>>builder()
                .message("성공적으로 목록 조회")
                .code("200")
                .data(objects)
                .build();
    }

    //게시물 생성
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) //201
    public ResponseEntity<Post> savePost(@RequestBody SavePostRequest request){

        log.info("Save post request {}", request);
        Post savedPost = repository.save(Post.of(request));

        return ResponseEntity.status(HttpStatus.CREATED).body(savedPost);
    }


    //단건 조회
    @GetMapping("/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Post> getPost(@PathVariable Long postId){
        Post findPost = repository.findById(postId);

        return ResponseEntity.ok(findPost);
    }


    //게시물 수정
    @PatchMapping("/{postId}")
    @ResponseStatus(HttpStatus.OK) //200
    public ResponseEntity<Post> updatePost(@PathVariable Long postId,
                                           @RequestBody UpdatePostRequest request){
        Post updatedPost = repository.update(postId, request);

        return ResponseEntity.ok(updatedPost);
    }

    //게시물 삭제
    @ResponseStatus(HttpStatus.NO_CONTENT) //204
    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long postId){
        repository.deleteById(postId);

        return ResponseEntity.noContent().build();
    }
}
