package io.dnlwjtud.blog.members.controller;

import io.dnlwjtud.blog.members.dto.LoginRequest;
import io.dnlwjtud.blog.members.dto.MemberSaveRequest;
import io.dnlwjtud.blog.members.dto.RefreshRequest;
import io.dnlwjtud.blog.members.dto.TokenResponse;
import io.dnlwjtud.blog.members.entity.Member;
import io.dnlwjtud.blog.members.repository.MemberJpaRepository;
import io.dnlwjtud.blog.members.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import tools.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:test-config.properties")
@Transactional // 각 테스트 후 롤백하여 데이터 일관성 유지
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper; // JSON 직렬화/역직렬화를 위해 필요

    @Autowired
    private MemberJpaRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthService authService; // 로그아웃 테스트를 위해 주입 (실제 서비스 호출)

    private static final String BASE_URL = "/api/v1/auth";

    @BeforeEach
    void setUp() {
        // 각 테스트 전에 데이터베이스를 정리합니다. @Transactional 덕분에 롤백되지만, 명시적으로 삭제하여 확실히 합니다.
        memberRepository.deleteAll();
    }

    @Test
    @DisplayName("회원가입 성공")
    void signup_Success() throws Exception {
        MemberSaveRequest request = new MemberSaveRequest("testuser", "password123", "test@example.com");

        mockMvc.perform(post(BASE_URL + "/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("testuser"))
                .andExpect(jsonPath("$.email").value("test@example.com"));
    }

    @Test
    @DisplayName("로그인 성공")
    void login_Success() throws Exception {
        // 회원가입 먼저
        String rawPassword = "password123";
        memberRepository.save(Member.builder()
                .username("loginuser")
                .password(passwordEncoder.encode(rawPassword))
                .email("login@example.com")
                .build());

        LoginRequest request = new LoginRequest("loginuser", rawPassword);

        mockMvc.perform(post(BASE_URL + "/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accessToken").exists())
                .andExpect(jsonPath("$.refreshToken").exists());
    }

    @Test
    @DisplayName("로그인 실패 - 사용자 없음")
    void login_UserNotFound_ShouldReturnResponse() throws Exception {
        LoginRequest request = new LoginRequest("nonexistent", "password");

        mockMvc.perform(post(BASE_URL + "/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isUnauthorized()) // BusinessException의 에러 코드에 따라 401 또는 404
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.code").value("A002")) // 기존 테스트에서 확인한 에러 코드
                .andExpect(jsonPath("$.message").value("존재하지 않는 사용자입니다")); // 기존 테스트에서 확인한 에러 메시지
    }

    @Test
    @DisplayName("로그인 실패 - 비밀번호 불일치")
    void login_InvalidPassword_ShouldReturnResponse() throws Exception {
        // 회원가입 먼저
        String rawPassword = "password123";
        memberRepository.save(Member.builder()
                .username("wrongpassuser")
                .password(passwordEncoder.encode(rawPassword))
                .email("wrongpass@example.com")
                .build());

        LoginRequest request = new LoginRequest("wrongpassuser", "wrongpassword");

        mockMvc.perform(post(BASE_URL + "/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.code").value("A003")) // 비밀번호 불일치 에러 코드 (가정)
                .andExpect(jsonPath("$.message").value("비밀번호가 일치하지 않습니다")); // 비밀번호 불일치 에러 메시지 (가정)
    }

    @Test
    @DisplayName("토큰 재발급 성공")
    void refresh_Success() throws Exception {
        // 1. 로그인하여 유효한 refreshToken을 얻습니다.
        String rawPassword = "password123";
        memberRepository.save(Member.builder()
                .username("refreshuser")
                .password(passwordEncoder.encode(rawPassword))
                .email("refresh@example.com")
                .build());

        LoginRequest loginRequest = new LoginRequest("refreshuser", rawPassword);
        String loginResponseContent = mockMvc.perform(post(BASE_URL + "/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        TokenResponse tokenResponse = objectMapper.readValue(loginResponseContent, TokenResponse.class);
        String refreshToken = tokenResponse.refreshToken();

        // 2. 얻은 refreshToken으로 재발급 요청을 보냅니다.
        RefreshRequest refreshRequest = new RefreshRequest(refreshToken);

        mockMvc.perform(post(BASE_URL + "/refresh")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(refreshRequest)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accessToken").exists())
                .andExpect(jsonPath("$.refreshToken").exists());
    }

    @Test
    @DisplayName("로그아웃 성공")
    void logout_Success() throws Exception {
        // 1. 로그인하여 토큰을 얻습니다. (로그아웃은 인증된 사용자만 가능하므로)
        String rawPassword = "password123";
        Member member = memberRepository.save(Member.builder()
                .username("logoutuser")
                .password(passwordEncoder.encode(rawPassword))
                .email("logout@example.com")
                .build());

        LoginRequest loginRequest = new LoginRequest("logoutuser", rawPassword);
        String loginResponseContent = mockMvc.perform(post(BASE_URL + "/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        TokenResponse tokenResponse = objectMapper.readValue(loginResponseContent, TokenResponse.class);
        String accessToken = tokenResponse.accessToken();

        // 2. 얻은 accessToken으로 로그아웃 요청을 보냅니다.
        // Spring Security Test를 사용하여 인증된 사용자 컨텍스트를 설정해야 합니다.
        // 현재는 토큰이 유효하다고 가정하고 진행합니다.
        // 실제로는 `with(SecurityMockMvcRequestPostProcessors.jwt().jwt(jwt -> jwt.tokenValue(accessToken)))`
        // 와 같은 방식으로 인증 컨텍스트를 설정해야 합니다.
        mockMvc.perform(post(BASE_URL + "/logout")
                        .header("Authorization", "Bearer " + accessToken)) // Access Token을 헤더에 추가
                .andDo(print())
                .andExpect(status().isNoContent()); // 204 No Content
    }
}