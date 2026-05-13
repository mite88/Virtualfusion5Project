package io.eddie.datademo.domain.dao.hibern;

import io.eddie.datademo.domain.Items;
import io.eddie.datademo.domain.util.TestUtil;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static io.eddie.datademo.domain.util.TestUtil.generateItem;
import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import static io.eddie.datademo.domain.util.TestUtil.generateItemList;


/**
 * packageName    : io.eddie.datademo.dao.hibern
 * fileName       : HibernateItemRepositoryTest
 * author         : Admin
 * date           : 26. 5. 12.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 5. 12.        Admin       최초 생성
 */
@SpringBootTest
class HibernateItemRepositoryTest {

    //테스트에서는 명시적으로 주는게 좋음
    @Autowired
    HibernateItemRepository repository;

    /*
      [JUnit5 테스트 라이프사이클 흐름]
      JPA 테스트 시 각 단계의 역할은 다음과 같습니다.

      @BeforeAll (before): 전체 테스트 시작 전 딱 1번 (공통 DB 초기화 등)
      @BeforeEach (beforeEach): 각 테스트 메서드 실행 직전. (테스트용 기본 데이터 저장)
      @Test: 실제 테스트 로직
      @AfterEach (afterEach): 각 테스트 종료 후 (데이터 정리)
      @AfterAll (after): 전체 테스트 종료 후
      */

    Items item; // BeforeEach에서 저장된 데이터를 담을 변수
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @BeforeEach
    void init() {
        // 모든 테스트 실행 전에 아이템 하나를 저장해둠
        item = repository.save(generateItem());
    }

    //테스트 시 어떤식으로 될거라는걸로 메서드명을 짓는게 좋음
    @Test
    void it_will_return_item_including_pk() {

        //given - @BeforeEach함
        //Items item = generateItems();
        //Items saved = repository.save(item);

        //System.out.println("saved.getId():"+saved.getId());

        //id 확인 들어갈때..
       /* if(saved.getId() == null){
            throw  new RuntimeException("저장안됨");
        }*/

        //then
        assertThat(item.getId()).isNotNull();
    }


    @Test
    void it_will_return_available_item() {
        //item 찾기
        //각 메서드는 독립적으로해야됨

        //given - @BeforeEach에서함
        //Items item = generateItems();
        //Items saved = repository.save(item);

        String targetCode = item.getCode();

        //잘못된것도 넣자
        //String targetCode = UUID.randomUUID().toString();

        //when
        Items findItem = repository.findByCode(targetCode);

        //then
        assertThat(item.getId()).isEqualTo(findItem.getId());
        assertThat(item.getCode()).isEqualTo(findItem.getCode());

    }

    //실패테스트
    @Test
    void it_will_return_null() {
        //given - @BeforeEach위에서함
        //Items item = generateItems();
        //Items saved = repository.save(item);

        final String unavailableCode = UUID.randomUUID().toString();

        //when
        Items findItem = repository.findByCode(unavailableCode);

        //then
        assertThat(findItem).isNull();

    }

    @Test
    void it_will_return_available_item_by_id() {

        //given - @BeforeEach위에서함
        //Items item = generateItems();
        //Items saved = repository.save(item);

        Long targetId = item.getId();

        Optional<Items> itemsOptional = repository.findById(targetId);
        assertThat(itemsOptional.isPresent()).isTrue();

        Items findItem = itemsOptional.get();
        assertThat(findItem).isNotNull();
        assertThat(findItem.getId()).isEqualTo(targetId);

    }

    //실패테스트
    @Test
    void it_will_return_empty_item_by_id() {

        Long UNAVAILABLE_ID = 10000L;

        Optional<Items> itemsOptional = repository.findById(UNAVAILABLE_ID);
        assertThat(itemsOptional.isPresent()).isFalse();

        //value가 null일 경우 NoSuchElementException을 발생
        //itemsOptional.get();
        assertThatThrownBy(
                () -> itemsOptional.get()
        ).isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void it_will_change_price(){

        final Integer TARGET_PRICE = 10000;
        repository.updatePrice(item.getCode(), TARGET_PRICE);

        //아이템 가져오기
        Items findItem = repository.findByCode(item.getCode());
        assertThat(findItem.getPrice()).isEqualTo(TARGET_PRICE);
    }


    @Test
    void it_will_raise_npe(){

        final Integer TARGET_PRICE = 10000;
        final String INVALID_CODE = "ITEM_CODE";
        repository.updatePrice(item.getCode(), TARGET_PRICE);

        assertThatThrownBy(()->repository.updatePrice(INVALID_CODE, TARGET_PRICE))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void it_will_return_all(){
        final int SIZE = 15;

        List<Items> items = generateItemList(15);
        items.stream().forEach(i -> repository.save(i));
        /*INSERT INTO VALUES (),(),().....();*/

        //벌크쿼리용 만들기 - 배치 밑메서드에서 할거임
        //repository.saveall

        List<Items> findItems = repository.findAll();

        //이대로하면 오류남
        //AssertThat(findItems.size()).isEqualTo(SIZE);
        assertThat(findItems.size()).isGreaterThan(SIZE);

    }

    //벌크 데이터 테스트
    @Test
    void it_will_save_all(){
        int SIZE = 1000;
        List<Items> items = generateItemList(SIZE);

        List<Items> savedList = repository.saveAll(items);

        assertThat(savedList.size()).isEqualTo(SIZE);

        savedList.forEach(
                item -> assertThat(item.getId()).isNotNull()
        );


    }



    //ctrl+rit + m 으로 매서드따로빼기
    //하고 F6으로 옮기기
    /*public static Integer genRandomPrice(){
        int price = (int) (Math.random() * 100_000);

        return price * 1_000;
    }

    private static Items generateItems(String n, String c, Integer p) {

        //Items item =  new Items(n, c, genRandomPrice());

        //@Items에  @Builder를 사용했기때문에 아래외 같이 가능함
        Items item = Items.builder()
                .name(genRandomItemCode())
                .code(genRandomItemCode())
                .price(genRandomPrice())
                .build();
        return item;
    }*/
}