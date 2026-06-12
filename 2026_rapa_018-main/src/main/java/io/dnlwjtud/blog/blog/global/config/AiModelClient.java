package io.dnlwjtud.blog.blog.global.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;

/**
 * packageName    : io.dnlwjtud.blog.blog.global.config
 * fileName       : AiModelClient
 * author         : Admin
 * date           : 26. 6. 12.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 6. 12.        Admin       최초 생성
 */
@Component
@RequiredArgsConstructor
public class AiModelClient {

    private final ClientConfig webClientBuilder;

    @Value("${ai.model.url}")
    private String aiModelUrl;

    @Value("${ai.model.endpoint:/predict}")
    private String aiModelEndpoint;

    @Value("${ai.model.timeout:5s}")
    private Duration timeout;

    public Object callModel(String input) {
        return webClientBuilder
                .baseUrl(aiModelUrl)
                .build()
                .post()
                .uri(aiModelEndpoint)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(input)            // 요청 바디 누락되어 있었음
                .retrieve()
                .bodyToMono(Object.class)
                .timeout(timeout)
                .block();
    }
}