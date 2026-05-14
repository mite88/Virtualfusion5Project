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
class QueryDslOrderRepositoryTest {

    @Autowired
    QueryDslItemRepository repository;

    Items SAVE_ITEM;

    @BeforeEach
    void init(){
        SAVE_ITEM = repository.save(TestUtil.generateItem());
    }



}