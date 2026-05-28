package io.eddie.restapi.repository;

import io.eddie.restapi.dto.UpdatePostRequest;
import io.eddie.restapi.entity.Post;
import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * packageName    : io.eddie.restapi.repository
 * fileName       : PostRepository
 * author         : Admin
 * date           : 26. 5. 28.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 5. 28.        Admin       최초 생성
 */
@Repository
public class PostRepository {

    private Map<Long, Post> db = new HashMap<>();

    @Getter
    private Long sequence = 0L;

    public Post save(Post post){
        post.setId(++sequence);
        db.put(post.getId(), post);
        return post;
    }

    public Post findById(Long id){
        return db.get(id);
    }

    public void deleteById(Long id){
        db.remove(id);
    }

    /*public Post updateById(Long id, Post post) {
        db.replace(id, post);
        return post;
    }*/

    public Post update(Long id, UpdatePostRequest request) {
      Post findPost = db.get(id);
      findPost.update(request);

      return db.replace(findPost.getId(), findPost);
    }


}
