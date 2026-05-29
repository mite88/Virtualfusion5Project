package io.eddie.bdd.dto;

public record MemberSignupCommand(
        String email,
        String name
) {
}
