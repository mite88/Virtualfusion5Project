package io.eddie.datademo.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * packageName    : io.eddie.datademo.domain
 * fileName       : items
 * author         : Admin
 * date           : 26. 5. 12.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 5. 12.        Admin       최초 생성
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Items {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true, nullable = false)
    private String code;

    @Setter
    private Integer price;

    private LocalDateTime createAt; //생성시간

    //생성자
    @Builder //이걸로 나중에 넣을때 순서 맞쳐서 넣게 해보자
    public Items(String name, String code, Integer price) {
        this.name = name;
        this.code = code;
        this.price = price;

        this.createAt = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Items items = (Items) o;
        return Objects.equals(id, items.id) && Objects.equals(code, items.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code);
    }
}
