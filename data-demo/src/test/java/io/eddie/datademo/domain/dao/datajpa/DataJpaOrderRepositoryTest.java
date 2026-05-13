package io.eddie.datademo.domain.dao.datajpa;

import io.eddie.datademo.domain.Orders;
import io.eddie.datademo.domain.util.TestUtil;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

/**
 * packageName    : io.eddie.datademo.domain.dao.datajpa
 * fileName       : DataJpaOrderItemRepositoryTest
 * author         : Admin
 * date           : 26. 5. 13.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 5. 13.        Admin       최초 생성
 */
@DataJpaTest
@Transactional
class DataJpaOrderRepositoryTest {

    @Autowired
    DataJpaOrderRepository orderRepository;

    @Test
    void test1(){
        String number = TestUtil.getNumStr();

        Orders saved = Orders.builder()
                .code(number)
                .build();

        orderRepository.save(saved);
        orderRepository.flush();

        Optional<Orders> ordersOptional = orderRepository.findByCode(number);
        ordersOptional.get();
    }


    @Test
    void test2(){
        String number = TestUtil.getNumStr();

        Orders saved = Orders.builder()
                .code(number)
                .build();

        orderRepository.save(saved);
        orderRepository.flush();

        Optional<Orders> ordersOptional = orderRepository.findByOrderCode(number);
        ordersOptional.get();
    }

}