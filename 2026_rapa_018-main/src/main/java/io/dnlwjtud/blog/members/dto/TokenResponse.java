package io.dnlwjtud.blog.members.dto;

public record TokenResponse(
        String accessToken,
        String refreshToken
) {}
