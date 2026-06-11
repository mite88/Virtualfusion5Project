package io.dnlwjtud.blog.members.config.handler;

import io.dnlwjtud.blog.members.dto.MemberDetails;
import io.dnlwjtud.blog.members.service.JwtTokenProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
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

        MemberDetails memberDetails = (MemberDetails) authentication.getPrincipal();

        Map<String, Object> claims = Map.of("username", memberDetails.getUsername()
                , "role", memberDetails.getRole().name());

        String accessToken = tokenProvider.issue(expiration, claims);

        response.addHeader("token", accessToken);

        response.addCookie(
                new Cookie("token", accessToken)
        );

    }
}
