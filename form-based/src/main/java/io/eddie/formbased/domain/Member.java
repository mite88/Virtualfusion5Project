package io.eddie.formbased.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.jdbc.DataSourceBuilder;

import javax.sql.DataSource;
import java.time.LocalDateTime;

/**
 * packageName    : io.eddie.formbased.domain
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

    //API
    //웹(js) -> api

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;

    private String email;

    private String role ="MANAGER";

    @Setter
    private LocalDateTime lastSignInAt;
    private LocalDateTime signUpAt = LocalDateTime.now();
    @Setter
    private LocalDateTime updatedAt = LocalDateTime.now();

    //생성자
    @Builder
    public Member(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }




}
