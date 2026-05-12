package io.eddie.datademo.book.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

/**
 * packageName    : io.eddie.datademo.book.entity
 * fileName       : Book
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
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(unique = true, nullable = false)
    private String isbn;

    private String author;
    private Integer price;

    @Builder.Default
    private Integer stock = 10;



}
