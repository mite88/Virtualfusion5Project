package io.eddie.jwt.config;

import io.eddie.jwt.config.handlers.OAuth2SuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsUtils;

/**
 * packageName    : io.eddie.jwt.config
 * fileName       : SecurityConfig
 * author         : Admin
 * date           : 26. 5. 22.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 5. 22.        Admin       최초 생성
 */
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final OAuth2SuccessHandler oAuth2SuccessHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                //보안정책용
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)

                //로그인관련
                .formLogin(AbstractHttpConfigurer::disable)
                .oauth2Login(Customizer.withDefaults())

                //세션관리
                .sessionManagement(
                        //이 설정은 애플리케이션이 웹 기반 세션(쿠키를 통해 사용자의 상태를 서버에 저장)에 의존할 것인지,
                        // 아니면 토큰(Token) 기반의 Stateless(상태 비저장) 방식을 사용할 것인지를 결정
                        session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                //인증, 인가관련
                .authorizeHttpRequests(
                        auth -> auth
                                /*
                                CORS (Cross-Origin Resource Sharing) 헤더에
                                대한 예비 요청(프리파일 요청)을 처리할 때 관련 구성이 필요합니다.
                                이는 다른 도메인에서 리소스에 접근하는 애플리케이션의 보안 정책입니다.
                                */
                                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                                .requestMatchers("/admin/**").hasAuthority("ADMIN")
                                .requestMatchers("/user/**").hasAuthority("USER")
                                .anyRequest().authenticated()
                )

                //oauth2 로그인관련
                .oauth2Login(
                        oauth2 -> oauth2
                                .successHandler(oAuth2SuccessHandler)
                )

                .build();
    }


}
