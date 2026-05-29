package io.eddie.bdd;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

@DisplayName("PostService 클래스의")
class PostServiceTests {

    PostService postService;

    @BeforeEach
    void setup() {
        PostRepository postRepository = new PostRepository();
        postService = new PostService(postRepository);
    }

    @Nested
    @DisplayName("save 메서드는")
    class Describe_save {

        @Nested
        @DisplayName("유효한 포스트 입력이 주어지면")
        class Context_with_valid_post {

            String TITLE = "제목";
            String CONTENTS = "내용";

            Post post = new Post(TITLE, CONTENTS);

            @Test
            @DisplayName("포스트를 db에 저장하고 id를 포함한 포스트를 반환한다.")
            void it_return_saved_post_with_id() {

                Post saved = postService.save(post);

                Assertions.assertNotNull(saved.getId());

                assertThat(saved.getTitle()).isEqualTo(TITLE);
                assertThat(saved.getContents()).isEqualTo(CONTENTS);

            }


        }




    }

}