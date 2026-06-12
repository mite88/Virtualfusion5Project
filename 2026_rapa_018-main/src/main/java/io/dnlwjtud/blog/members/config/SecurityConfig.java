package io.dnlwjtud.blog.members.config;

import io.dnlwjtud.blog.members.config.filter.TokenAuthenticationFilter;
import io.dnlwjtud.blog.members.config.handler.OAuth2SuccessHandler;
import io.dnlwjtud.blog.members.service.OAuth2MemberService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final TokenAuthenticationFilter tokenAuthenticationFilter;
    private final OAuth2MemberService oAuth2MemberService;
    private final OAuth2SuccessHandler oAuth2SuccessHandler;

    @Bean
    public SecurityFilterChain filterChain(
            HttpSecurity http,
            @Qualifier("authenticationSuccessHandlerImpl") AuthenticationSuccessHandler successHandler,
            AuthenticationFailureHandler failureHandler
    ) throws Exception {
        return http
                .httpBasic(basic -> basic.disable())
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.disable())
                .headers(headers -> headers.frameOptions(frame -> frame.disable()))
                .formLogin(f -> f
                        .successHandler(successHandler)
                        .failureHandler(failureHandler)
                )
                .oauth2Login(oauth -> oauth
                        .userInfoEndpoint(ui -> ui.userService(oAuth2MemberService))
                        .successHandler(oAuth2SuccessHandler)
                )
                .sessionManagement(config -> config.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(ex -> ex.authenticationEntryPoint(
                        (req, resp, e) -> resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "인증에 실패하였습니다!")
                ))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        .requestMatchers("/api/v1/auth/signup", "/api/v1/auth/login", "/api/v1/auth/refresh").permitAll()
                        .requestMatchers("/api/v1/auth/logout").authenticated()
                        .requestMatchers(HttpMethod.GET, "/posts/**").permitAll()
                        .requestMatchers("/posts/**").authenticated()
                        .requestMatchers(HttpMethod.POST, "/members").permitAll()
                        .requestMatchers("/members/**").authenticated()
                        .anyRequest().permitAll()
                )
                .addFilterBefore(tokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

}
