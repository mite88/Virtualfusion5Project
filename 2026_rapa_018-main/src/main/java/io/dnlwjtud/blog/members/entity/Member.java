package io.dnlwjtud.blog.members.entity;

import io.dnlwjtud.blog.members.dto.Role;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;

    private String email;

    private String providerId; // OAuth2 (e.g. google sub)

    @Enumerated(EnumType.STRING)
    private Role role;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Builder
    public Member(String username, String password, String email, String providerId) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.providerId = providerId;
        this.role = Role.MEMBER;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }


}
