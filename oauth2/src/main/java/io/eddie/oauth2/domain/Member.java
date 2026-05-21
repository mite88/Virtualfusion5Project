package io.eddie.oauth2.domain;

import aQute.bnd.annotation.headers.BundleContributors;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * packageName    : io.eddie.oauth2.domain
 * fileName       : Member
 * author         : Admin
 * date           : 26. 5. 21.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 5. 21.        Admin       최초 생성
 */
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String nickname;
    private String role = "USER";

    //중복회원막는용
    @Column(unique = true, nullable = false)
    private String email;

    private String provider;//로그인어디서 했는지

    private LocalDateTime createAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();

    @Builder
    public Member(String name, String nickname, String email, String provider) {
        this.name = name;
        this.nickname = nickname;
        this.email = email;
        this.provider = provider;
    }

}
