package io.eddie.jwt.service;

import io.eddie.jwt.config.properties.JwtProperties;
import io.eddie.jwt.dao.Role;
import io.eddie.jwt.dto.KeyPair;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

/**
 * packageName    : io.eddie.jwt.config
 * fileName       : TokenProvider
 * author         : Admin
 * date           : 26. 5. 22.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 5. 22.        Admin       최초 생성
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TokenProvider {
    private final JwtProperties jwtProperties;
    Date cur = new Date();

    //yaml에서 불러오기
    //spel로 작성
    //@Value("${custom.jwt.secrets.app-key}")
    //private String SECRET_KEY_STR;

    private final JwtProperties properties;

    //환경변수로빼기
    //Access Token : ?min = 1,800,000
    //Refresh Token : ?min = 604,800,000
    public KeyPair issueKeyPair(Long id, Role role){
        String accessToken = issueAccessToken(id, role);
        String refreshToken = issueRefreshToken(id, role);

        return KeyPair.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public String issueAccessToken(long id, Role role) {
        return issue(id, role, jwtProperties.getValidation().getAccess());
    }

    public String issueRefreshToken(long id, Role role) {
        return issue(id, role, jwtProperties.getValidation().getRefresh());
    }


    //회원등급, id

    private String issue(Long id, Role role, Long validTime) {

        JwtBuilder jwtBuilder = Jwts.builder()
                .subject(id.toString())
                .claim("role", role.getValue())
                .issuedAt(cur)
                .expiration(new Date(cur.getTime() + validTime)) //만료일
                .signWith(getSecretKey());//서명

        return jwtBuilder.compact();

    }

    //키만드는 메서드
    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(jwtProperties.getSecrets().getAppKey().getBytes());
    }


    //유효성검사
    public boolean isValidateToken(String token){

       try {
           Jwts.parser()
                   .verifyWith(getSecretKey())
                   .build()
                   .parseSignedClaims(token);//서명열기

           return true;
           // JwtException 발생 시 (JWT 관련 문제)
       } catch (JwtException e) {
           // [권장] 문구를 먼저 쓰고, 가장 마지막 인자로 예외 객체 e를 전달합니다.
           log.error("[JWT 인증 에러] 토큰 검증에 실패했습니다. {}", e.getMessage(), e);
       } catch (IllegalStateException e) {
           // IllegalStateException 발생 시 (상태 관리 문제)
           log.error("⚠[상태 조건 에러] 비즈니스 로직상 유효하지 않은 상태입니다. {}", e.getMessage(), e);
       } catch (Exception e) {
           // 그 외 모든 예외 발생 시 (치명적인 시스템 오류)
           log.error("[시스템 오류] 예상치 못한 심각한 오류가 발생했습니다.", e);
       }
       
        return false;
    }

}
