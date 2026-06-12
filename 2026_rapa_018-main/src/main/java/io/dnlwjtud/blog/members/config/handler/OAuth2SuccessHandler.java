package io.dnlwjtud.blog.members.config.handler;

import io.dnlwjtud.blog.members.dto.MemberDetails;
import io.dnlwjtud.blog.members.dto.TokenResponse;
import io.dnlwjtud.blog.members.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

    private final AuthService authService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        MemberDetails details = (MemberDetails) authentication.getPrincipal();
        TokenResponse tokens = authService.issueTokens(details.getUsername(), details.getRole().name());

        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(
                "{\"accessToken\":\"" + tokens.accessToken() + "\",\"refreshToken\":\"" + tokens.refreshToken() + "\"}"
        );
    }

}
