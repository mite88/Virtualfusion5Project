package io.eddie.bdd.dto;

public record MemberSignupResult(
        Long memberId,
        String email,
        String name
) {
}
