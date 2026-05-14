package io.eddie.datademo.domain.dao.qrydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import io.eddie.datademo.domain.Items;
import io.eddie.datademo.domain.QItems;
import io.eddie.datademo.domain.util.TestUtil;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * packageName    : io.eddie.datademo.domain.dao.qrydsl
 * fileName       : QueryDslItemRepository
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
public class QueryDslItemRepository {

    private final JPAQueryFactory queryFactory;
    private final EntityManager entityManager;

    //Querydsl이 조회에 특화되어있어서 원래는 조회를하는게 좋음
    //컴바인하는방법으로 추가가능

    //저장
    @Transactional
    public Items save(Items item){
        entityManager.persist(item);

        return item;
    }

    //조회
    @Transactional(readOnly = true)
    public Optional<Items> findById(Long id){
       /* entityManager.find(Items.class, id);

        String jpql = "select i from Items i where i.id = :id";
        entityManager.createQuery(jpql).setParameter("id", id).getResultList();*/

        //"select i from Items i where i.id = :id
        queryFactory.selectFrom(QItems.items)
                .where(QItems.items.id.eq(id))
                .fetchFirst();

        return Optional.ofNullable(entityManager.find(Items.class, id));
    }

    @Transactional
    public Optional<Items> findByCode(String code){

        queryFactory.selectFrom(QItems.items)
                .where(QItems.items.code.eq(code))
                .fetchFirst();

        return Optional.ofNullable(entityManager.find(Items.class, code));
    }

    //수정
    @Transactional
    public void update(Long id, Integer price) {
        Items findItem = entityManager.find(Items.class, id);
        findItem.setPrice(price);
    }


    //삭제
    public void delete(Items items) {
        entityManager.remove(items);
    }
}
