package io.eddie.datademo.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName    : io.eddie.datademo.domain
 * fileName       : orderItems
 * author         : Admin
 * date           : 26. 5. 12.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 5. 12.        Admin       최초 생성
 */
/*@Accessors(chain = true)*/
@Entity
@Getter
@Table(name = "order_items")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String code;

    private Integer quantity;

    //때에따라서 어느 주문인지 확인해야하기때문에

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_code", referencedColumnName = "code")
    private Orders order;

    @ManyToOne
    @JoinColumn(name = "item_code", referencedColumnName = "code")
    private Items items;

   public OrderItems setOrders(Orders order){
        this.order = order;
        order.getOrderItems().add(this);

        return this;
    }

    //생성자
    public OrderItems(String code, Integer quantity) {
        this.code = code;
        this.quantity = quantity;
    }
}
