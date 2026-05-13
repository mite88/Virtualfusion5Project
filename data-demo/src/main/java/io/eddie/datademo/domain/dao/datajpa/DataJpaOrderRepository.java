package io.eddie.datademo.domain.dao.datajpa;

import io.eddie.datademo.domain.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

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
public interface DataJpaOrderRepository extends JpaRepository<Orders, Long> {

    Optional<Orders> findByCode(String code);

    //jpql 직접 입력해보기
    @Query("select o from Orders o where o.code = :orderCode")
    Optional<Orders> findByOrderCode(String orderCode);

    //sql문으로 : 권장하진않음
    /*@Query(value = "select * from orders o where code = :code", nativeQuery = true)
    Optional<Orders> findByOrderCode2(String orderCode);*/

}
