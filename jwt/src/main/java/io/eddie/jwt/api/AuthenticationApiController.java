package io.eddie.jwt.api;

import io.eddie.jwt.dao.TokenRepository;
import io.eddie.jwt.domain.RefreshToken;
import io.eddie.jwt.service.TokenProvider;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class AuthenticationApiController {

    private final TokenRepository repository;
    private final TokenProvider tokenProvider;

    @GetMapping("/refresh")
    public ResponseEntity<Void> refreshToken(HttpServletRequest request) {

        String bearerToken = request.getHeader("Authorization");

        if (bearerToken == null || !bearerToken.startsWith("Bearer ")) {
            throw new IllegalArgumentException("Invalid Authorization header"); // 401
        }

        // token validate
        if ( !tokenProvider.validate("") ) {
            throw new IllegalArgumentException("Invalid Authorization header"); // 401
        }

        // refresh - cookie
        request.getCookies();

        // refresh - header
        request.getHeaderNames();

        Optional<RefreshToken> ref = repository.findValidRefTokenByToken("ref");

        RefreshToken refreshToken = ref.orElseThrow(() -> new IllegalStateException("Refresh token not found"));// 401

        String accessToken = tokenProvider.issueAccessToken(refreshToken.getMember().getId(), refreshToken.getMember().getRole());


        return ResponseEntity.noContent().build();
    }

}
