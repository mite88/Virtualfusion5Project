package io.eddie.datademo.book.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * packageName    : io.eddie.datademo.book.entity
 * fileName       : Rental
 * author         : Admin
 * date           : 26. 5. 12.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 5. 12.        Admin       최초 생성
 */
@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED) // JPA 기본 생성자
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    private LocalDateTime rentalDate;
    private LocalDateTime returnDate;

    @Enumerated(EnumType.STRING)
    private RentalStatus status;


    @Builder
    public Rental(Member member, Book book) { // 'menber' 오타도 수정됨
        this.member = member;
        this.book = book;
        this.status = RentalStatus.RENTED;
        this.rentalDate = LocalDateTime.now();
    }

    //반납했을때용 생성자
    public void returnBook() {
        this.status = RentalStatus.RETURNED;
        this.returnDate = LocalDateTime.now();
    }
}
