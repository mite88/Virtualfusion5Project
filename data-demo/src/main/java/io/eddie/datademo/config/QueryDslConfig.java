package io.eddie.datademo.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * packageName    : io.eddie.datademo.config
 * fileName       : QueryDslConfig
 * author         : Admin
 * date           : 26. 5. 14.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 5. 14.        Admin       최초 생성
 */
@Configuration
public class QueryDslConfig {

    //메서드에서 반환되는 객체(JPAQueryFactory)를 스프링 컨테이너에 등록
    //epository나 Service 어디에서든 @RequiredArgsConstructor를 통해 주입받아 사용할 수 있음
    @Bean
    public JPAQueryFactory jpaQueryFactory(EntityManager entityManager){ // 이름을 jpaQueryFactory로 변경
        return new JPAQueryFactory(entityManager);
    }
}
