package io.eddie.jwt.config.handlers;

import io.eddie.jwt.dto.KeyPair;
import io.eddie.jwt.dto.Role;
import io.eddie.jwt.service.TokenProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler
        extends SimpleUrlAuthenticationSuccessHandler {

    @Value("${custom.jwt.redirection.base}")
    private String baseUrl;

    private final TokenProvider tokenProvider;

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication)
            throws IOException, ServletException {

        //        super.onAuthenticationSuccess(request, response, authentication);

        log.info("onAuthenticationSuccess");

        Long mockId = 1L;
        Role mockRole = Role.MEMBER;

        KeyPair keyPair = tokenProvider.issueKeyPair(mockId, mockRole);

        response.setHeader("X-RAPA-ACCESS-TOKEN", keyPair.accessToken());
        response.setHeader("X-RAPA-REFRESH-TOKEN", keyPair.refreshToken());

//        "http://localhost:3000/auth"
        getRedirectStrategy().sendRedirect(request, response, genUrlStr(keyPair));

    }

    private String genUrlStr(KeyPair keyPair) {
        return UriComponentsBuilder.fromUriString(baseUrl)
                .queryParam("access", keyPair.accessToken())
                .queryParam("refresh", keyPair.refreshToken())
                .build()
                .toUri()
                .toString();
    }



}
