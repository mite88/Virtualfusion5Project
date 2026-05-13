package io.eddie.datademo.book.dao.hibern;

import io.eddie.datademo.book.entity.Book;
import io.eddie.datademo.book.entity.Rental;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

/**
 * packageName    : io.eddie.datademo.book.dao.hibern
 * fileName       : HibernateBookRepository
 * author         : Admin
 * date           : 26. 5. 12.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 26. 5. 12.        Admin       최초 생성
 */
@RequiredArgsConstructor
@Transactional //각 테스트가 끝날 때마다 데이터를 자동으로 롤백(삭제)하여 다음 테스트에 영향을 주지 않음
@Repository
public class HibernateBookRepository {


    private final EntityManager entityManager;

    //item crud (create, read, update, delete)

    //추가 : isbn 찾는거 추가
    public Optional<Book> findByIsbn(String isbn) {
        return entityManager.createQuery("select b from Book b where b.isbn = :isbn", Book.class)
                .setParameter("isbn", isbn)
                .getResultStream()
                .findFirst();
    }


    /**
     * [ JPA Entity Manager 핵심 작업 가이드 ]
     *
     * 1. 저장 (Create)
     *    - 메서드: em.persist(entity)
     *    - 포인트: 저장 즉시 객체의 @Id 필드에 값이 채워짐 (영속화)
     */
    public Book createBook(Book book){
        /*entityManager.persist(book);
        return book;*/

        //  DB 제약 조건에 의존하기 전에 먼저 조회해본다 (안전장치)
        Optional<Book> existing = findByIsbn(book.getIsbn());
        if (existing.isPresent()) {
            return null; // 이미 있으면 즉시 null 반환
        }

        try {
            entityManager.persist(book);
            entityManager.flush(); // 즉시 DB 제약조건 확인!
            return book;
        } catch (Exception e) {
            // 로그를 남겨서 어떤 오류인지 확인은 하되, 프로그램은 계속 진행됨
            System.out.println("저장 실패: " + e.getMessage());
            return null; // 오류 발생 시 null 반환
        }
    }

    /**
     * 2. 단건 조회 (Read)
     *    - 메서드: em.find(Entity.class, id)
     *    - 포인트: 결과가 없을 수 있으므로 Optional.ofNullable()로 감싸서 반환 권장
     */
    public Optional<Book> read(Long id){
        //entityManager.find(Book.class, id);
        return Optional.ofNullable(
           entityManager.find(Book.class, id)
        );
    }

    /** 3. 목록/조건 조회 (Search)
     *    - 메서드: em.createQuery("JPQL", Entity.class)
     *    - 포인트: 테이블명이 아닌 '엔티티 이름'과 '필드명'을 대소문자 구분하여 사용
     */

    public List<Book> search(int id){
        return entityManager.createQuery("select i from Book i where " +
                        "i.id > :id", Book.class)
                .setParameter("id", id)
                .getResultList();

    }


    /** 4. 수정 (Update)
     *    - 방식: 변경 감지 (Dirty Checking)
     *    - 포인트: em.find()로 가져온 객체의 값만 변경하면, 트랜잭션 종료 시 자동 반영 (save 필요 없음)
     */
    public void updateStatus(Long id, Book updateParam){
        /*Optional<Book> bookOptional = read(id);

        //값 존재 시 바꿈
        bookOptional.ifPresent(book -> {
            book.setIsbn(isbn);
        });*/

        Book book = entityManager.find(Book.class, id);

        if (book != null) {
            // 엔티티 내부의 setter나 update 메서드 활용
            book.setTitle(updateParam.getTitle());
            book.setAuthor(updateParam.getAuthor());
            book.setIsbn(updateParam.getIsbn());
            book.setPrice(updateParam.getPrice());
        }

    }


    /** 5. 삭제 (Delete)
     *    - 메서드: em.remove(entity)
     *    - 포인트: 반드시 em.find()로 먼저 조회하여 '영속 상태'인 객체를 넘겨야 삭제 가능
     */
    public void delete(Long id){
        // 대상조회
        Book book = entityManager.find(Book.class, id);

        // 존재 시 삭제
        if(book != null){
            entityManager.remove(book);
        }

    }
}
