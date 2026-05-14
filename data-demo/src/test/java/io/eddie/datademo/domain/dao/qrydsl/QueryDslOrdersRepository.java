package io.eddie.datademo.domain.dao.qrydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import io.eddie.datademo.domain.*;
import io.eddie.datademo.domain.dao.datajpa.DataJpaOrderItemRepository;
import io.eddie.datademo.domain.dao.datajpa.DataJpaOrderRepository;
import io.eddie.datademo.domain.exceptions.CouldNotFindSuchOrderException;
import io.eddie.datademo.domain.exceptions.CouldNotFindSuchOrderItemException;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

/**
 * packageName    : io.eddie.datademo.domain.dao.qrydsl
 * fileName       : QueryDslOrdersRepository
 * author         : Admin
 * date           : 26. 5. 14.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 5. 14.        Admin       최초 생성
 */
@Repository
@Slf4j
@RequiredArgsConstructor
public class QueryDslOrdersRepository {

    private final JPAQueryFactory queryFactory;
    private final EntityManager entityManager;

    private final DataJpaOrderRepository orderRepository;
    private final DataJpaOrderItemRepository orderItemRepository;

    @Transactional
    public Orders saveOrder(Orders orders){
        return orderRepository.save(orders);
    }

    //pageable 받을수 있군 ㅇㅊㅇ
    public Page<Orders> findAll(Pageable pageable){
        return orderRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public List<Orders> findAll(){
        return queryFactory.selectFrom(QOrders.orders).fetch();
    }

    @Transactional(readOnly = true)
    public Optional<Orders> findOrderByCode(String code){
        return Optional.ofNullable(queryFactory.selectFrom(QOrders.orders)
                .where(QOrders.orders.code.eq(code)).
                fetchOne());
    }

    @Transactional(readOnly = true)
    public Orders getOrderByCode(String code){
        return findOrderByCode(code).orElseThrow(CouldNotFindSuchOrderException::new);
    }

    //삭제
    @Transactional
    public void removeOrder(Orders orders){
        orderRepository.delete(orders);
    }

    @Transactional
    public void removeOrderCode(String code){
        //orderRepository.findByOrderCode(code).orElseThrow(CouldNotFindSuchOrderException::new);

        Orders targetOrders = getOrderByCode(code);
        orderRepository.delete(targetOrders);
    }

    //orderItems
    //생성
    @Transactional
    public OrderItems saveOrderItem(OrderItems orderItems){
        return orderItemRepository.save(orderItems);
    }

    @Transactional(readOnly = true)
    public Page<OrderItems> findAllOrderItems(Pageable pageable){
        //return orderItemRepository.findAll();
        return orderItemRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public List<OrderItems> findAllOrderItemsByOrderCode(String code){
        /*queryFactory.selectFrom(QOrderItems.orderItems)
                .where(QOrderItems.orderItems.order.code.eq(code));*/

        //join
        return  queryFactory.selectFrom(QOrderItems.orderItems)
                .leftJoin(QOrderItems.orderItems.order, QOrders.orders)
                .leftJoin(QOrderItems.orderItems.item, QItems.items)
                .where(QOrders.orders.code.eq(code))
                .fetch();
    }

    @Transactional
    public void deleteOrderItem(OrderItems orderItems){
        orderItemRepository.delete(orderItems);
    }

    public Optional<OrderItems> findOrderItemById(Long id){

        return Optional.ofNullable(queryFactory.selectFrom(QOrderItems.orderItems)
                .where(QOrderItems.orderItems.id.eq(id))
                .fetchOne());
    }


    public OrderItems getOrderItem(Long id) {
        return findOrderItemById(id)
                .orElseThrow(CouldNotFindSuchOrderItemException::new); // 여기서 Optional이 벗겨집니다.
    }

    @Transactional
    public void updateOrderItem(Long orderItemId, int targetQty){

        //orderItems.setQuantity(targetQty);

        OrderItems orderItems = getOrderItem(orderItemId);
        orderItems.setQuantity(targetQty);

    }


}
