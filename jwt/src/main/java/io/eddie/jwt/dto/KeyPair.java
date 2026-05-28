package io.eddie.jwt.dto;

public record KeyPair(
        String accessToken,
        String refreshToken
) {
}
