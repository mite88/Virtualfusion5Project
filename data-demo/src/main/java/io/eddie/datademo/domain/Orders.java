package io.eddie.datademo.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.bind.annotation.ResponseStatus;

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
@Table(name = "orders")
@Builder // 빌더 패턴 사용 가능하게 함
@AllArgsConstructor // 빌더를 위한 전인자 생성자
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String code;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    //item관련추가
    @Builder.Default
    private List<OrderItems> orderItems = new ArrayList<>();

    @Builder.Default
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
