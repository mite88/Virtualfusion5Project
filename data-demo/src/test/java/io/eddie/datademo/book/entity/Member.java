package io.eddie.datademo.book.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * packageName    : io.eddie.datademo.book.entity
 * fileName       : Member
 * author         : Admin
 * date           : 26. 5. 12.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 5. 12.        Admin       최초 생성
 */
@Builder
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본 생성자 필수
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = false)
    private String email;
    private String address;

    @Builder.Default
    private LocalDateTime createAt = LocalDateTime.now(); // 가입일

}
