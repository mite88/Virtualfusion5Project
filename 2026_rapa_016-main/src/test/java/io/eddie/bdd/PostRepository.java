package io.eddie.bdd;

import java.util.HashMap;
import java.util.Map;

public class PostRepository {

    private static final Map<Long, Post> db = new HashMap<>();
    private Long sequence = 0L;

    public Post save(Post post) {

        sequence++;

        post.setId(sequence);
        db.put(post.getId(), post);

        return post;

    }

    public Post findById(Long id) {
        return db.get(id);
    }

    public void deleteById(Long id) {
        db.remove(id);
    }

}
