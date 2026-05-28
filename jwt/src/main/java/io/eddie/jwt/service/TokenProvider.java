package io.eddie.jwt.service;

import io.eddie.jwt.config.properties.JwtProperties;
import io.eddie.jwt.dto.KeyPair;
import io.eddie.jwt.dto.Role;
import io.eddie.jwt.dto.TokenBody;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
public class TokenProvider {

    private final JwtProperties jwtProperties;

    public KeyPair issueKeyPair(Long id, Role role) {

        String accessToken = issueAccessToken(id, role);
        String refreshToken = issueRefreshToken(id, role);

        return new KeyPair(accessToken, refreshToken);

    }

    public String issueAccessToken(Long id, Role role) {
        return issue(id, role, jwtProperties.getValidations().getAccess());
    }

    public String issueRefreshToken(Long id, Role role) {
        return issue(id, role, jwtProperties.getValidations().getRefresh());
    }

    private String issue(Long id, Role role, Long validTime) {
        return Jwts.builder()
                .subject(id.toString())
                .claim("role", role.getValue())
                .issuedAt(new Date())
                .expiration(new Date(new Date().getTime() + validTime))
                .signWith(getSecretKey())
            .compact();
    }

    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(jwtProperties.getSecrets().getAppKey().getBytes());
    }

    public boolean validate(String token) {

        try {
            parseClaims(token);
            return true;
        } catch ( JwtException e ) {
            log.error("Token validation failed: {}", e.getMessage());
        } catch ( IllegalStateException e ) {
            log.error("Illegal state during token validation");
        } catch ( Exception e ) {
            log.error("Unexpected error during token validation: {}", e.getMessage());
        }

        return false;
    }

    public Jws<Claims> parseClaims(String token) {
        return Jwts.parser()
            .verifyWith(getSecretKey())
            .build()
            .parseSignedClaims(token);
    }

    public TokenBody parseJwt(String token) {

        Jws<Claims> claimsJws = parseClaims(token);

        String sub = claimsJws.getPayload().getSubject();
        Object role = claimsJws.getPayload().get("role");

        return new TokenBody(
            Long.parseLong(sub),
            Role.valueOf(role.toString())
        );

    }


}
