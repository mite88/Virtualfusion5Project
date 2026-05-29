package io.eddie.bdd.controller;

import io.eddie.bdd.dto.MemberSignupCommand;
import io.eddie.bdd.dto.MemberSignupRequest;
import io.eddie.bdd.dto.MemberSignupResult;
import io.eddie.bdd.servivce.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MemberController.class)
@DisplayName("MemberController 클래스의")
class MemberControllerTests {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    MemberService memberService;

    @Autowired
    ObjectMapper om;

    @Nested
    @DisplayName("POST /members 엔드포인트는")
    class Describe_Signup {

        @Nested
        @DisplayName("유효한 회원 정보가 담긴 요청이면")
        class Context_with_valid_request {

            String EMAIL = "test@example.com";
            String NAME = "John Doe";

            MemberSignupRequest REQUEST = new MemberSignupRequest(EMAIL, NAME);

            @Test
            @DisplayName("201 Created 상태와 회원 정보를 반환한다")
            void it_should_return_201_created_and_response_body() throws Exception {

                Long memberId = 1L;

                MemberSignupResult memberSignupResult = new MemberSignupResult(memberId, EMAIL, NAME);

                given(
                        memberService.signup(any(MemberSignupCommand.class))
                ).willReturn(memberSignupResult);

                mockMvc.perform(
                    post("/members")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(om.writeValueAsString(REQUEST))
                ).andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.memberId").value(memberId))
                .andExpect(jsonPath("$.email").value(EMAIL))
                .andExpect(jsonPath("$.name").value(NAME))
                .andDo(print());


            }

        }

    }


}