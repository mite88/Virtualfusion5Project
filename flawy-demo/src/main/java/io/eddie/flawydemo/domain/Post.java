package io.eddie.flawydemo.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

/**
 * packageName    : io.eddie.flawydemo.domain
 * fileName       : Post
 * author         : Admin
 * date           : 26. 5. 14.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 5. 14.        Admin       최초 생성
 */
@Entity
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY) // 성능을 위해 LAZY 로딩 권장
    @JoinColumn(name = "board_id")
    private Board board;

    // 추가함
    private String status;

    private LocalDateTime createdAt;
    private LocalDateTime updateAt;
}
