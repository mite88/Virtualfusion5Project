package io.eddie.flawydemo.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

/**
 * packageName    : io.eddie.flawydemo.domain
 * fileName       : Board
 * author         : Admin
 * date           : 26. 5. 14.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 5. 14.        Admin       최초 생성
 */
@Entity
@Table(name = "boards")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private LocalDateTime createdAt;
    private LocalDateTime updateAt;

}
