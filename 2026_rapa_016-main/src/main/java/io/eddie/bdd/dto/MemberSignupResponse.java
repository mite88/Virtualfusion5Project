package io.eddie.bdd.dto;

public record MemberSignupResponse(
        Long memberId,
        String email,
        String name
) {
}
