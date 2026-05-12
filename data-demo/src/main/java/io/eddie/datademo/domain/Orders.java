package io.eddie.datademo.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * packageName    : io.eddie.datademo.domain
 * fileName       : Orders
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
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String code;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    //item관련추가
    private List<OrderItems> orderItems = new ArrayList<>();

    private LocalDateTime orderAt = LocalDateTime.now();

    //생성자
    public Orders(String code,  List<OrderItems> items) {
        this.code = code;

        if(items != null){
            this.orderItems.addAll(items);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Orders orders = (Orders) o;
        return Objects.equals(id, orders.id) && Objects.equals(code, orders.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code);
    }
}
