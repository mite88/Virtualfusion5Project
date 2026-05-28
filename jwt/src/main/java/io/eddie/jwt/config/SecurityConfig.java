package io.eddie.jwt.config;

import io.eddie.jwt.config.handlers.JwtAuthenticationFilter;
import io.eddie.jwt.config.handlers.OAuth2SuccessHandler;
import io.eddie.jwt.service.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsUtils;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final OAuth2SuccessHandler oauth2SuccessHandler;
//    private final TokenProvider tokenProvider;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {
        return http

                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.disable())
                .httpBasic(httpBasic -> httpBasic.disable())

                .formLogin(form -> form.disable())
                .oauth2Login(
                        oauth2 -> oauth2.successHandler(oauth2SuccessHandler)
                )

                .sessionManagement( config -> config.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .authorizeHttpRequests( auth -> auth
                        .requestMatchers(CorsUtils::isPreFlightRequest)
                            .permitAll()
                        .requestMatchers("/admin/**")
                            .hasAuthority("ADMIN")
                        .requestMatchers("/member/**")
                            .hasAuthority("MEMBER")
                        .anyRequest()
                            .authenticated()
                )

                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)

                .build();

    }

}
