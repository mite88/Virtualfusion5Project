package io.dnlwjtud.blog.members.config.handler;

import io.dnlwjtud.blog.members.dto.MemberDetails;
import io.dnlwjtud.blog.members.service.JwtTokenProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;


@Component
@RequiredArgsConstructor
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

    private final JwtTokenProvider tokenProvider;

    @Value("${custom.jwt.expiration}")
    private long expiration;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request
            , HttpServletResponse response
            , Authentication authentication)
            throws IOException, ServletException {

        Map<String, Object> claims = createClaims(authentication.getPrincipal());

        String accessToken = tokenProvider.issue(expiration, claims);

        response.addHeader("token", accessToken);

        response.addCookie(
                new Cookie("token", accessToken)
        );

    }

    private Map<String, Object> createClaims(Object principal) {
        if (principal instanceof MemberDetails memberDetails) {
            return Map.of("username", memberDetails.getUsername()
                    , "role", memberDetails.getRole().name());
        }

        if (principal instanceof OAuth2User oauth2User) {
            return Map.of("username", oauth2User.getAttribute("username")
                    , "role", oauth2User.getAttribute("role"));
        }

        throw new IllegalArgumentException("Unsupported principal type: " + principal.getClass().getName());
    }
}
