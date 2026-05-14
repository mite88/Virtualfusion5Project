package io.eddie.datademo.domain;

import jakarta.persistence.*;
import lombok.*;

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
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String code;

    private Integer quantity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_code", referencedColumnName = "code")
    private Orders order;

    @ManyToOne
    @JoinColumn(name = "item_code", referencedColumnName = "code")
    private Items item;

    public OrderItems(String code, Integer quantity) {
        this.code = code;
        this.quantity = quantity;
    }

    public OrderItems setOrder(Orders order) {

        this.order = order;
        order.getOrderItems().add(this);

        return this;

    }


    public void setQuantity(int targetQty) {
        this.quantity = targetQty;
    }
}