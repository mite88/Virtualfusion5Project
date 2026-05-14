package io.eddie.datademo.domain.dao.datajpa;

import io.eddie.datademo.domain.OrderItems;
import io.eddie.datademo.domain.Orders;
import io.eddie.datademo.domain.exceptions.CouldNotFindSuchOrderException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * packageName    : io.eddie.datademo.domain.dao.datajpa
 * fileName       : DataJpaOrderRepositoryCombie
 * author         : Admin
 * date           : 26. 5. 13.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 5. 13.        Admin       최초 생성
 */
@Repository
@RequiredArgsConstructor
public class DataJpaOrderRepositoryCombine implements OrderRepository{

    private final DataJpaOrderRepository orderRepository;
    private final DataJpaOrderItemRepository orderItemRepository;

    @Override
    @Transactional(readOnly = true) //읽기 전용
    public Orders saveOrders(Orders orders) {
        return orderRepository.save(orders);
    }

    @Override
    public Optional<Orders> findOrderByOrderCode(String orderCode) {
        return orderRepository.findByOrderCode(orderCode);
    }

    @Override
    public String removeOrderByOrderCode(String orderCode) {
        Orders findorder = orderRepository.findByCode(orderCode)
                //만든 exception 을 여기서 사용
                .orElseThrow( CouldNotFindSuchOrderException::new);

        orderRepository.delete(findorder);

        return "";
    }

    @Override
    public OrderItems saveOrderItems(OrderItems orderItems) {
        return orderItemRepository.save(orderItems);
    }


}
