package io.eddie.jwt.app;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;import org.junit.jupiter.api.Test;
import javax.crypto.SecretKey;
import java.util.Date;
import java.util.logging.Logger;

/**
 * packageName    : io.eddie.jwt.app
 * fileName       : JwtTokenTests
 * author         : Admin
 * date           : 26. 5. 22.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 5. 22.        Admin       최초 생성
 */
@Slf4j
public class JwtTokenTests {

    @Test
    void it_will_issue_a_token(){
        Date cur = new Date();
        Date exp = new Date(cur.getTime() + 30000L);

        String secretKeyStr = "019e4de2-ed25-7c83-a903-c403ef9351b6-019e4de3-25cb-7bff-a990-58b158979292";

        SecretKey key = Keys.hmacShaKeyFor(secretKeyStr.getBytes());

        JwtBuilder jwtBuilder = Jwts.builder()
                .subject("Hello, World!")
                .claim("name", "eddie")
                .issuedAt(cur)
                .expiration(exp) //만료일
                .signWith(key) ;//서명

        String token = jwtBuilder.compact();

        log.info("token: {}", token);

        //JwtBuilder subject = jwtBuilder.subject("Hello, World!");

    }

}
