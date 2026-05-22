package io.eddie.jwt.config.handlers;

import io.eddie.jwt.dao.Role;
import io.eddie.jwt.dto.KeyPair;
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
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.Map;

/**
 * packageName    : io.eddie.jwt.config.handlers
 * fileName       : OAuth2SuccessHandler
 * author         : Admin
 * date           : 26. 5. 22.
 * description    : 로그인 성공/핸들러
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 5. 22.        Admin       최초 생성
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final TokenProvider tokenProvider;


    @Value("${custom.jwt.redirection-base}")
    private final String baseUrl;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        log.info("OAuth2 Login Success");

        Long mockId = 1L;
        Role mockRole = Role.MEMBER;

        KeyPair keyPair = tokenProvider.issueKeyPair(mockId, mockRole);

        //header 에 보내기
        response.setHeader("X-RAPA-ACCESS-TOKEN", keyPair.getAccessToken());
        response.setHeader("X-RAPA-REFRESH-TOKEN", keyPair.getRefreshToken());

        //URL 생성
        String targetUrl = genUrlStr(keyPair);

        //로그인되고 나서 이동....
        //super.onAuthenticationSuccess(request, response, authentication);

        //리다이렉트
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }

    private String genUrlStr(KeyPair keyPair) {
        return UriComponentsBuilder.fromUriString(baseUrl)
                .queryParam("access", keyPair.getAccessToken())
                .queryParam("refresh", keyPair.getRefreshToken())
                .build()
                .toUriString();
    }


}
