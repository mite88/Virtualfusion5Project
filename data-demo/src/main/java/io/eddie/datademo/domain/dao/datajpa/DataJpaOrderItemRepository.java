package io.eddie.datademo.domain.dao.datajpa;

import io.eddie.datademo.domain.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * packageName    : io.eddie.datademo.domain.dao.datajpa
 * fileName       : DataJpaOrderItemRepository
 * author         : Admin
 * date           : 26. 5. 13.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 5. 13.        Admin       최초 생성
 */
public interface DataJpaOrderItemRepository extends JpaRepository<OrderItems, Long> {

    @Query("""
    select 
        oi 
    from 
        OrderItems oi 
    where 
        oi.order.code = :orderCode
    """)
    List<OrderItems> findAllByOrderCode(String orderCode);



}
