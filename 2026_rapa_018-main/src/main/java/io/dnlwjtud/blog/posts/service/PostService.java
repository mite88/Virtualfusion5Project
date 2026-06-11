package io.dnlwjtud.blog.posts.service;

import io.dnlwjtud.blog.members.entity.Member;
import io.dnlwjtud.blog.members.service.MemberService;
import io.dnlwjtud.blog.posts.dto.EditPostRequest;
import io.dnlwjtud.blog.posts.dto.PostDescription;
import io.dnlwjtud.blog.posts.entity.Posts;
import io.dnlwjtud.blog.posts.exceptions.UnAuthorizedUpdateException;
import io.dnlwjtud.blog.posts.mapper.PostMapper;
import io.dnlwjtud.blog.posts.repository.PostJpaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostJpaRepository repository;
    private final MemberService memberService;

    @Transactional
    public PostDescription save(EditPostRequest request, String username) {

        Posts post = Posts.builder()
                .title(request.title())
                .content(request.content())
                .build();

        Member findMember = memberService.findByUsername(username);

        post.setAuthor(findMember);

        Posts saved = repository.save(post);

        return PostMapper.toDescription(saved);

    }

    public PostDescription findById(Long id) {
        Optional<Posts> postOptional = repository.findById(id);

        Posts findPost = postOptional.orElseThrow(NoSuchElementException::new);

        return PostMapper.toDescription(findPost);
    }

    public List<PostDescription> findAll() {

        List<Posts> posts = repository.findAll();

        List<PostDescription> postDescriptions = new ArrayList<>();

        for ( Posts post : posts ) {
            PostDescription description = PostMapper.toDescription(post);
            postDescriptions.add(description);
        }

        return postDescriptions;

    }

    @Transactional
    public PostDescription updatePost(EditPostRequest request, Long id, String username) {

        Optional<Posts> postOptional = repository.findById(id);

        Posts findPost = postOptional.orElseThrow(NoSuchElementException::new);

        if ( !findPost.getAuthor().getUsername().equals(username) ) {
            throw new UnAuthorizedUpdateException("본인 글이 아니면 수정할 수 없습니다.");

        }

        findPost.update(request);

        return PostMapper.toDescription(findPost);

    }

    @Transactional
    public void deletePost(Long id) {
        repository.deleteById(id);
    }

}
