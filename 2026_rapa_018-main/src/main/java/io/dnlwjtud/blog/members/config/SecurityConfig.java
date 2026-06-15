package io.dnlwjtud.blog.members.config;

import io.dnlwjtud.blog.members.config.filter.TokenAuthenticationFilter;
<<<<<<< HEAD
import io.dnlwjtud.blog.members.service.GoogleOAuth2MemberService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
=======
import io.dnlwjtud.blog.members.config.handler.OAuth2SuccessHandler;
import io.dnlwjtud.blog.members.service.OAuth2MemberService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
>>>>>>> 1c874ab668465e2fe8b05767c841230044bbc07e
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
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
<<<<<<< HEAD
            HttpSecurity http
            , AuthenticationSuccessHandler successHandler
            , AuthenticationFailureHandler failureHandler
            , GoogleOAuth2MemberService googleOAuth2MemberService
            , ObjectProvider<ClientRegistrationRepository> clientRegistrationRepository
    ) throws Exception {
        HttpSecurity security = http
                .httpBasic(basic -> basic.disable()) // http basic disable
                .csrf(csrf -> csrf.disable()) // csrf disable
                .cors(cors -> cors.disable()) // cors disable
                .headers(
                        headers -> headers.frameOptions(frame -> frame.disable())
=======
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
>>>>>>> 1c874ab668465e2fe8b05767c841230044bbc07e
                )
                .oauth2Login(oauth -> oauth
                        .userInfoEndpoint(ui -> ui.userService(oAuth2MemberService))
                        .successHandler(oAuth2SuccessHandler)
                )
<<<<<<< HEAD

                .sessionManagement( config -> config.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED) )

=======
                .sessionManagement(config -> config.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
>>>>>>> 1c874ab668465e2fe8b05767c841230044bbc07e
                .exceptionHandling(ex -> ex.authenticationEntryPoint(
                        (req, resp, e) -> resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "인증에 실패하였습니다!")
                ))
<<<<<<< HEAD

                .authorizeHttpRequests(
                        auth -> auth

                                .requestMatchers("/swagger-ui/**").permitAll()
                                .requestMatchers("/oauth2/**", "/login/oauth2/**").permitAll()

                                .requestMatchers(HttpMethod.GET, "/posts/**").permitAll()
                                .requestMatchers("/posts/**").authenticated()

                                .requestMatchers(HttpMethod.POST, "/members").permitAll()
                                .requestMatchers(HttpMethod.POST, "/api/v1/auth/signup").permitAll()
                                .requestMatchers("/members/**").authenticated()

                                .anyRequest().permitAll()
                )


                .addFilterBefore(tokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        if (clientRegistrationRepository.getIfAvailable() != null) {
            security.oauth2Login(
                    oauth2 -> oauth2
                            .userInfoEndpoint(userInfo -> userInfo.userService(googleOAuth2MemberService))
                            .successHandler(successHandler)
                            .failureHandler(failureHandler)
            );
        }

        return security.build();

=======
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
>>>>>>> 1c874ab668465e2fe8b05767c841230044bbc07e
    }

}
