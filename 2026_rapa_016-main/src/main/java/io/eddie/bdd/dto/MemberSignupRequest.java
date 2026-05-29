package io.eddie.bdd.dto;

public record MemberSignupRequest(
        String email,
        String name
) {
}
