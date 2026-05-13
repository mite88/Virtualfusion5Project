package io.eddie.datademo.domain.dao.datajpa;

import io.eddie.datademo.domain.Items;
import io.eddie.datademo.domain.dao.DataJpaItemRepository;
import io.eddie.datademo.domain.util.TestUtil;
import jakarta.transaction.Transactional;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

/**
 * packageName    : io.eddie.datademo.domain.dao.datajpa
 * fileName       : DataJpaItemRepositoryTests
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
public class DataJpaItemRepositoryTests {

    @Autowired
    DataJpaItemRepository repository;

    Items item;

    @BeforeEach
    void init(){
        item = repository.save(TestUtil.generateItem());
    }


    // 저장 테스트
    @Test
    void it_will_save() {
        Items items = TestUtil.generateItem();

        repository.save(items);
    }

    //찾기 테스트
    @Test
    void it_will_find(){
       Long targetId = item.getId();

       Optional<Items> found = repository.findById(targetId);
       assertThat(found.isPresent()).isTrue();

       Items findItem = found.get();
       assertThat(item.getCode()).isEqualTo(findItem.getCode());

    }

    //수정테스트
    void it_will_update(){
        // given(자동으로 final 붙여줌)
        Integer targetPrice = 40000;
        Long targetId = item.getId();

        // when
         Optional<Items> itemsOptional = repository.findById(targetId);
         assertThat(itemsOptional.isPresent()).isTrue();

        // 영속성 매니저(컨텍스트)가 해당 엔티티를 관리 중 (1차 캐시에 보관)
         Items findItem = itemsOptional.get();
         findItem.setPrice(targetPrice);

        // 변경 감지
        // 따로 repository.save()를 호출하지 않아도 됨

        // then
        // 트랜잭션이 끝나는 시점(또는 flush 시점)에
        // Hibernate가 스냅샷과 현재 엔티티를 비교해 UPDATE 쿼리를 자동으로 실행함

        // 같은 영속성 컨텍스트 내이므로 동일성(==)도 보장됨
        Items updatedItem = repository.findById(targetId).get();
        assertThat(updatedItem.getPrice()).isEqualTo(targetPrice);
    }

    //삭제 테스트
    @Test
    void it_will_delete(){
        Long targetId = item.getId();
       /*
        repository.deleteById(targetId);*/
        //assertThat(repository.existsById(targetId)).isFalse();
        //db는 삭제되지만 객체(참조)에 한해서는 가비지 컬렉션(JVM이 관리)으로 인해 바로 삭제되진않음
        //그래서 nullPointException 이 발생하지 않음
        repository.delete(item);

        // DB와 영속성 컨텍스트 관점: 더 이상 존재하지 않아야 함
        Optional<Items> findItem = repository.findById(targetId);
        assertThat(findItem.isPresent()).isFalse();

        // 2객체 관점 (주석에 질문하신 부분):
        // item 객체 자체는 Java 힙 메모리에 여전히 남아있음 (GC가 치우기 전까지)
        // 하지만 JPA가 관리하는 상태는 아니게 됨
        assertThat(item.getId()).isNotNull(); // 여전히 ID값은 들고 있음 (메모리상에 존재하니까)
    }
}
