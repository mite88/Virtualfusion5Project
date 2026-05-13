package io.eddie.datademo.domain.dao.datajpa;

import io.eddie.datademo.domain.OrderItems;
import io.eddie.datademo.domain.Orders;

import java.util.Optional;

/**
 * packageName    : io.eddie.datademo.domain.dao.datajpa
 * fileName       : OrderRepository
 * author         : Admin
 * date           : 26. 5. 13.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 5. 13.        Admin       최초 생성
 */
//두가지를 합쳐보자 (Orders, Items)
public interface OrderRepository {


    Orders saveOrders(Orders orders);
    Optional<Orders> findOrderByOrderCode(String orderCode);
    String removeOrderByOrderCode(String orderCode);

    OrderItems saveOrderItems(OrderItems orderItems);
}
