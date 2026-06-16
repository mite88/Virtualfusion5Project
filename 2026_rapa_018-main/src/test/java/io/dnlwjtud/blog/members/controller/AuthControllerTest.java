package io.dnlwjtud.blog.members.controller;

import io.dnlwjtud.blog.members.dto.LoginRequest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.client.RestClient;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:test-config.properties")
class AuthControllerTest {

    @org.springframework.boot.test.web.server.LocalServerPort
    private int port;

    @Test
    void login_UserNotFound_ShouldReturnResponse() {
        RestClient restClient = RestClient.builder()
                .baseUrl("http://localhost:" + port)
                .build();

        ResponseEntity<String> response = restClient.post()
                .uri("/api/v1/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .body(new LoginRequest("testuser1", "password"))
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (request, httpResponse) -> {})
                .toEntity(String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
        assertThat(response.getBody())
                .contains("\"success\":false")
                .contains("User not found with username: testuser1");
    }
}