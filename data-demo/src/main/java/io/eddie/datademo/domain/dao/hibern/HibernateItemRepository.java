package io.eddie.datademo.domain.dao.hibern;

import io.eddie.datademo.domain.Items;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * packageName    : io.eddie.datademo.dao.hibern
 * fileName       : HibernateItemRepositoryTests
 * author         : Admin
 * date           : 26. 5. 12.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 5. 12.        Admin       최초 생성
 */
@Slf4j
@Repository
@Transactional //
@RequiredArgsConstructor
public class HibernateItemRepository {

    private final EntityManager entityManager;

    //item crud (create, read, update, delete)

    public Items save(Items item){
        entityManager.persist(item);
        return item;
    }

    public Items findByCode(String code){
        //entityManager.find(Item.class, id);

        // Test에서 확인하고 try/catch 하고 마무리하자
        try {
            Items findItem = entityManager.createQuery("select i from Items i where i.code = :code", Items.class)
                    .setParameter("code", code)
                    .getSingleResult();

            return findItem;
        }catch (Exception e){
            return null;
        }

    }

    public Optional<Items> findById(Long id){
        //Items item = entityManager.find(Items.class, id);

        /*if( item == null){
            return Optional.empty();
        }else{
            return Optional.of(item);
        }*/

        return Optional.ofNullable(entityManager.find(Items.class, id));
    }

    public void updatePrice(String itemCode, int price){
        Items findItem = findByCode(itemCode);
        findItem.setPrice(price);
    }

    public List<Items> findAll(){
        return entityManager.createQuery("select i from Items i", Items.class).getResultList();
    }

    //벌크쿼리
    public List<Items> saveAll(List<Items> items){
        //일반적
        //items.forEach(i -> entityManager.persist(i));
        //이렇게 하면 자료 많으면 오래 걸리기 때문에 배치를 사용함

        final int BATCH_SIZE = 50;
        //batch : 일괄처리
        for(int i = 0; i <items.size(); i++){
            entityManager.persist(items.get(i));
            if(i % BATCH_SIZE == 0 && i > 0){
                entityManager.flush();
                entityManager.clear();
                log.info("flush!");
            }
        }

        //어중간하게 남을 수 있기때문
        entityManager.flush();
        entityManager.clear();
        log.info("flush!");

        //IntStream.range(0, BATCH_SIZE)


        return items;
    }
}
