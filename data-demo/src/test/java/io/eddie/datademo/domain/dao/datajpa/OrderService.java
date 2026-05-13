package io.eddie.datademo.domain.dao.datajpa;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * packageName    : io.eddie.datademo.domain.dao.datajpa
 * fileName       : OrderService
 * author         : Admin
 * date           : 26. 5. 13.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 5. 13.        Admin       최초 생성
 */
@Service
@RequiredArgsConstructor
public class OrderService {

    private OrderRepository orderRepository;

    void save(){
        //orderRepository.saveOrders();


    }
}
