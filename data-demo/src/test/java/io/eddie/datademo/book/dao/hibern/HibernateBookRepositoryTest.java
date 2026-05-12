package io.eddie.datademo.book.dao.hibern;

import io.eddie.datademo.book.entity.Book;
import io.eddie.datademo.book.util.TestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static io.eddie.datademo.book.util.TestUtil.*;

/**
 * packageName    : io.eddie.datademo.book.dao.hibern
 * fileName       : HibernateBookRepositoryTest
 * author         : Admin
 * date           : 26. 5. 12.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 5. 12.        Admin       최초 생성
 */

/**
 * [ JPA Repository Test 실행 원칙 ]
 *
 * 1. 격리성 (Isolation)
 *    - 각 테스트 메서드는 독립적이어야 함.
 *    - @BeforeEach를 활용해 테스트마다 신규 데이터를 생성하여 영향도를 차단함.
 *
 * 2. 준비-실행-검증 (Given-When-Then)
 *    - Given: TestUtil 등을 사용해 엔티티 객체 생성 및 기초 데이터 저장.
 *    - When: 리포지토리의 실제 메서드(save, find, update 등) 호출.
 *    - Then: AssertJ(assertThat)를 사용하여 결과값의 일치 여부 검증.
 *
 * 3. 변경 감지 검증 (Dirty Checking Test)
 *    - 엔티티의 필드를 수정한 후 다시 조회했을 때 값이 바뀌어 있는지 확인.
 *    - 테스트 클래스에 @Transactional이 있으면 자동 롤백되므로 DB 오염 걱정 없음.
 *
 * 4. 연관관계 테스트 (Rental)
 *    - Rental 저장 전, 반드시 연관된 Member와 Book이 먼저 저장(persist)되어
 *      영속성 컨텍스트에 ID가 존재하는 상태여야 함.
 *
 * 5. 삭제 검증 (Delete)
 *    - 삭제 메서드 호출 후, findById로 조회했을 때 Optional.isEmpty()가 true인지 확인.
 */

/**
 * [ JPA 실패 테스트(Negative Test) 원칙 ]
 *
 * 1. 예외 발생 검증 (Exception Assertions)
 *    - assertThatThrownBy(() -> logic)를 사용하여 특정 예외가 발생하는지 확인.
 *
 * 2. 데이터 부재 (Empty Result)
 *    - 존재하지 않는 ID로 조회 시 Optional.isEmpty()가 true인지 확인.
 *
 * 3. 제약 조건 위반 (Constraints)
 *    - 중복된 ISBN이나 Email 저장 시 DB 에러(DataIntegrityViolationException) 발생 확인.
 *
 * 4. 비즈니스 로직 실패
 *    - 재고가 0인 책을 빌리거나, 이미 반납된 책을 또 반납할 때의 에러 처리 검증.
 *
 * 5. NULL 체크
 *    - 필수값(Title, Name 등)에 null을 넣고 저장했을 때 에러가 나는지 확인.
 */
@SpringBootTest
public class HibernateBookRepositoryTest {

    @Autowired
    HibernateBookRepository repository;

    Book book;

    @BeforeEach
    void init(){
        book = repository.createBook(generateBook());
    }

    //create
    @Test
    void Book_들어가는거_테스트(){
        /*
         - Given: TestUtil 등을 사용해 엔티티 객체 생성 및 기초 데이터 저장.
         - When: 리포지토리의 실제 메서드(save, find, update 등) 호출.
         - Then: AssertJ(assertThat)를 사용하여 결과값의 일치 여부 검증.
        */

        //Given
        Book book = generateBook();

        //When
        Book created = repository.createBook(book);

        //Then
        assertThat(created.getId()).isNotNull();
    }

    //create 실패테스트
    @Test
    void Book_빈값삽입_테스트() {
        // Given
        Long invalidId = 99999L;

        // When
        Optional<Book> found = repository.read(invalidId);

        // Then
        assertThat(found.isEmpty()).isTrue();
    }

    @Test
    void Book_값은값_저장_테스트() {
        // Given
        Book book1 = TestUtil.generateBook();
        repository.createBook(book1);

        // 같은 ISBN을 가진 다른 객체 생성
        Book book2 = Book.builder()
                .title("다른 책")
                .isbn(book1.getIsbn()) // 중복 ISBN
                .author("다른 저자")
                .build();

        // When & Then
        /*assertThatThrownBy(() -> {
            repository.createBook(book2);
        }).isInstanceOf(Exception.class);*/
        Book result = repository.createBook(book2);
        assertThat(result).isNull();

    }
}
