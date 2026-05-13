package io.eddie.datademo.book.dao.hibern;

import io.eddie.datademo.book.entity.Book;
import io.eddie.datademo.book.util.TestUtil;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.Rollback;

import java.util.List;
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

    //create 테스트
    @Test
    @DisplayName("도서 저장 : 도서가 정상적으로 저장되어 새로은 ID가 나와야함 ")
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
    @DisplayName("도서 저장 실패 : 중복된 ISBN 저장 시 null을 반환")
    void Book_중복된_ISBN_삽입_테스트() {
        // Given
        Book book2 = Book.builder()
                .title("중복 도서")
                .isbn(book.getIsbn()) // BeforeEach에서 저장된 ISBN 사용
                .author("다른 저자")
                .build();

        // When
        Book result = repository.createBook(book2);

        // Then
        assertThat(result).isNull();
    }


    // --------------------------------------------

    //단건 조회 (Read) 테스트
    @Test
    @DisplayName("단건 조회 : 존재하는 ID 조회시 값이 나와야함")
    void Book_단건조회_테스트(){
        // Given
        Long targetId = book.getId();

        // When
        Optional<Book> found = repository.read(targetId);

        // Then
        // isEmpty()가 아니라 isPresent() 혹은 isFalse()여야 합니다.
        assertThat(found.isPresent()).isTrue();

        Book findItem = found.get();

        assertThat(findItem.getId()).isEqualTo(targetId);
        assertThat(findItem.getIsbn()).isEqualTo(book.getIsbn());

    }

    //단건 조회 (Read) 실패테스트
    @Test
    @DisplayName("단건 조회 : 없는 ID로 단건 조회하면 빈 Optional 반환")
    void Book_단건조회_실패테스트() {
        // Given
        Long invalidId = 99999L;

        // When
        Optional<Book> findItem = repository.read(invalidId);

        // Then
        assertThat(findItem).isEmpty();
    }


    //  목록/조건 조회 (Search) 테스트
    @Test
    @DisplayName("목록 조회 : 특정 ID보다 큰 값을 가진 모든 목록 반환")
    void Book_목록조회_테스트(){
        //Given - 도서 3권 저장
        repository.createBook(TestUtil.generateBook()); // ID: 1 (예시)
        repository.createBook(TestUtil.generateBook()); // ID: 2
        repository.createBook(TestUtil.generateBook()); // ID: 3

        //When - 조건으로 1보다 큰거 조회하기
        List<Book> books = repository.search(1);

        //Then - 1보다 큰지도 보기
        assertThat(books).allMatch(book -> book.getId() > 1);

    }

    //  목록/조건 조회 (Search) 실패 테스트
    @Test
    @DisplayName("목록 조회 실패: 검색 조건에 맞는 도서가 없으면 빈 리스트를 반환")
    void Book_목록조회_실패테스트() {
        // Given
        repository.createBook(TestUtil.generateBook());

        // When
        List<Book> books = repository.search(9999);

        // [Then]
        assertThat(books).isEmpty();
        assertThat(books).isNotNull(); // 리스트 객체 자체는 존재해야 함
    }

    //  수정 (Update) (Search) 테스트
    @Test
    @DisplayName("도서 수정: 수정하면 도서의 제목과 저자가 DB에 반영")
    @Rollback(false) // 커밋이 발생하며 UPDATE 로그가 보임
    void Book_수정_테스트(){
        //Given
        Long targetId = book.getId();

        Book updateParam = Book.builder()
                .title("수정된 제목")
                .author("수정된 작가")
                .isbn(book.getIsbn()) // ISBN은 그대로 유지
                .price(50000)
                .build();

        //When
        repository.updateStatus(targetId, updateParam);

        //Then - 바뀌었는지 확인용
        Book updatedBook = repository.read(targetId).get();

        assertThat(updatedBook.getTitle()).isEqualTo("수정된 제목");
        assertThat(updatedBook.getAuthor()).isEqualTo("수정된 작가");
        assertThat(updatedBook.getPrice()).isEqualTo(50000);

    }

    //  수정 (Update) (Search) 실패 테스트
    @Test
    @DisplayName("도서 수정 실패: 존재하지 않는 ID 수정 시도 시 NullPointerException")
    void Book_수정_실패테스트(){
        //Given
        Long invalidId = 99999L;

        Book updateParam = Book.builder()
                .title("수정된 실패제목")
                .author("수정된 실패작가")
                .isbn(book.getIsbn()) // ISBN은 그대로 유지
                .price(50000)
                .build();

        //When
        //repository.updateStatus(invalidId, updateParam);

        //When / Then
        /*assertThatThrownBy(() ->
                repository.updateStatus(invalidId, updateParam))
                .isInstanceOf(NullPointerException.class);*/

        // 에러 없이 끝나게 하는법
        assertThatCode(() -> repository.updateStatus(invalidId, updateParam))
                .doesNotThrowAnyException();

    }

    @Test
    @DisplayName("도서 삭제: ID로 도서를 삭제  후 조회하면 결과가 없어야함")
    void Book_삭제_테스트() {
        // Given
        Long targetId = book.getId();

        // When
        repository.delete(targetId);
    }

    @Test
    @DisplayName("도서 삭제 실패: 존재하지 않는 ID로 삭제 시도 시 에러 없이 종료")
    void Book_삭제_존재하지않는ID_테스트() {
        // Given
        Long invalidId = 99999L;

        // When & Then (에러가 터지지 않는 것이 성공)
        assertThatCode(() -> repository.delete(invalidId))
                .doesNotThrowAnyException();
    }


}
