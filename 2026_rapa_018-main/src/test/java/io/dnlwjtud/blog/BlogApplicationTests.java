package io.dnlwjtud.blog;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test") // 'test' 프로필을 활성화하여 Redis 설정을 로드하지 않도록 함
class BlogApplicationTests {

    @Test
    void contextLoads() {
    }

}
