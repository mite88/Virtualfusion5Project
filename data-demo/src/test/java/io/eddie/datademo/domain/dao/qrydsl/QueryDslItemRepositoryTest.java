package io.eddie.datademo.domain.dao.qrydsl;

import io.eddie.datademo.domain.Items;
import io.eddie.datademo.domain.util.TestUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * packageName    : io.eddie.datademo.domain.dao.qrydsl
 * fileName       : QueryDslItemRepositoryTest
 * author         : Admin
 * date           : 26. 5. 14.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 5. 14.        Admin       최초 생성
 */
@Slf4j
@Transactional
@SpringBootTest
class QueryDslItemRepositoryTest {

    @Autowired
    QueryDslItemRepository repository;

    Items SAVE_ITEM;

    @BeforeEach
    void init(){
        SAVE_ITEM = repository.save(TestUtil.generateItem());
    }

    @Test
    void it_will_save_item(){

        Items item = TestUtil.generateItem();
        Items result = repository.save(item);
        assertNotNull(result.getId());

    }

    @Test
    void it_will_find_one(){

        Optional<Items> itemOpt = repository.findById(SAVE_ITEM.getId());
        assertTrue(itemOpt.isPresent());


        Items item = repository.findById(SAVE_ITEM.getId()).get();
        log.info("item = {}", item.toString());
        assertEquals(item.getId(), SAVE_ITEM.getId());
    }

    //삭제
    @Test
    void it_will_delete_one(){
        Long targetId = SAVE_ITEM.getId();
        repository.findById(targetId).ifPresent(repository::delete);
        assertThat(repository.findById(targetId)).isEmpty();
    }

}