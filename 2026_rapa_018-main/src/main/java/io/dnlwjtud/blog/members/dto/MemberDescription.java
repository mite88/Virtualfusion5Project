package io.dnlwjtud.blog.members.dto;

import java.time.LocalDateTime;

public record MemberDescription(

        String username,
        String email,
        Role role,

        LocalDateTime signedAt

) {
}
