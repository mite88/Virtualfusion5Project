package io.dnlwjtud.blog.members.service;

import io.dnlwjtud.blog.members.dto.LoginRequest;
import io.dnlwjtud.blog.members.dto.MemberDetails;
import io.dnlwjtud.blog.members.dto.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberService memberService;
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenService refreshTokenService;
    private final PasswordEncoder passwordEncoder;

    @Value("${custom.jwt.expiration}")
    private long accessExpiration;

    @Value("${custom.jwt.refresh-expiration}")
    private long refreshExpiration;

    public TokenResponse login(LoginRequest request) {
        MemberDetails details = (MemberDetails) memberService.loadUserByUsername(request.username());

        if (!passwordEncoder.matches(request.password(), details.getPassword())) {
            throw new BadCredentialsException("비밀번호가 일치하지 않습니다.");
        }

        return issueTokens(details.getUsername(), details.getRole().name());
    }

    public TokenResponse refresh(String refreshToken) {
        if (!jwtTokenProvider.validate(refreshToken)) {
            throw new BadCredentialsException("유효하지 않은 RefreshToken입니다.");
        }

        Map<String, Object> claims = jwtTokenProvider.getClaims(refreshToken);
        String username = claims.get("username").toString();

        String stored = refreshTokenService.find(username)
                .orElseThrow(() -> new BadCredentialsException("만료되었거나 로그아웃된 RefreshToken입니다."));

        if (!stored.equals(refreshToken)) {
            throw new BadCredentialsException("RefreshToken이 일치하지 않습니다.");
        }

        MemberDetails details = (MemberDetails) memberService.loadUserByUsername(username);
        return issueTokens(username, details.getRole().name());
    }

    public void logout(String username) {
        refreshTokenService.delete(username);
    }

    public TokenResponse issueTokens(String username, String role) {
        String accessToken = jwtTokenProvider.issue(accessExpiration, Map.of("username", username, "role", role));
        String refreshToken = jwtTokenProvider.issueRefreshToken(refreshExpiration, username);
        refreshTokenService.save(username, refreshToken);
        return new TokenResponse(accessToken, refreshToken);
    }

}
