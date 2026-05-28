package io.eddie.jwt.dto;

public record TokenBody(
    Long memberId,
    Role role
) {
}
