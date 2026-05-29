package io.eddie.bdd;

public class PostService {

    private final PostRepository repository;

    public PostService(PostRepository repository) {
        this.repository = repository;
    }

    public Post save(Post post) {
        Post save = repository.save(post);
        return save;
    }

}
