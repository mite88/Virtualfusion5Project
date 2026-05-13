package io.eddie.datademo.domain.dao;


import io.eddie.datademo.domain.Items;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * packageName    : io.eddie.datademo.domain.dao.hibern
 * fileName       : dataJpaIUtemRepository
 * author         : Admin
 * date           : 26. 5. 13.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 5. 13.        Admin       최초 생성
 */
public interface DataJpaItemRepository extends JpaRepository<Items, Long> {

    //main으로 올리기

}
