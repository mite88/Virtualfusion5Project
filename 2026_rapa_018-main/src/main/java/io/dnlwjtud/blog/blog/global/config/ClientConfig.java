package io.dnlwjtud.blog.blog.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * packageName    : io.dnlwjtud.blog.blog.global.config
 * fileName       : ClientConfig
 * author         : Admin
 * date           : 26. 6. 12.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 6. 12.        Admin       최초 생성
 */
@Configuration
public class ClientConfig {

    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }
}