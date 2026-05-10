import jakarta.persistence.*;
import model.s03.Member;

import java.util.List;
import java.util.stream.IntStream;

public class PagingTest {


    static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("hibernate-exp-6");

    void main() {

        setup();

//        fixed_paging_test();
        scroll_page_test();

        tearDown();

    }

    void setup() {

        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction tx = entityManager.getTransaction();

        try {

            tx.begin();

            IntStream.rangeClosed(1, 15)
                .forEach(i -> {
                    String username = "user_%d".formatted(i);
                    Member member = new Member(username, "pwd%d".formatted(i), "%s@email.com".formatted(username));
                    entityManager.persist(member);
                });

            tx.commit();

        } catch ( Exception e ) {
            tx.rollback();
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }

    }

    void tearDown() {
        emf.close();
    }

    /*
    SQL 중
    limit - 결과 중 처음부터 몇 개 가져올 것인가
    offset - 어디서부터 가져올 것인지

    select [속성,..] from [테이블명] limit {n} offset {m};
     */
    void paging(int offset, int pageSize) {

        EntityManager em = emf.createEntityManager();
        String jpql = "select m from Member m order by m.id desc";

        TypedQuery<Member> query = em.createQuery(jpql, Member.class);

        TypedQuery<Member> pagingQuery = query.setFirstResult(offset) // offset 부여
                .setMaxResults(pageSize);// limit 부여

        List<Member> pagedMembers = pagingQuery.getResultList();

        System.out.println("페이지 결과 =====");
        System.out.println("조회된 회원 수 : " + pagedMembers.size());
        pagedMembers.forEach(m -> System.out.println("회원명 : " + m.getName()));

    }

    void fixed_paging_test() {

        // 한 페이지당 5개의 컨텐츠를 불러오도록 설정
        // 첫 번째(0번째 인덱스) 페이지 조회
        final int PAGE_NUM = 0;
        final int PAGE_SIZE = 5;

        paging(PAGE_NUM, PAGE_SIZE);
        System.out.println();

    }

    void scroll_page_test() {

        // 페이지 3개를 스크롤링
        IntStream.rangeClosed(0, 2)
            .forEach(i -> {

                final int PAGE_SIZE = 5;

                System.out.println("%d번 째 페이지".formatted(i+1));
                paging(i*PAGE_SIZE, PAGE_SIZE);
                System.out.println();

            });

        /*
        출력 예시
        1번 째 페이지
        페이지 결과 =====
        조회된 회원 수 : 5
        회원명 : user_15
        회원명 : user_14
        회원명 : user_13
        회원명 : user_12
        회원명 : user_11

        2번 째 페이지
        페이지 결과 =====
        조회된 회원 수 : 5
        회원명 : user_10
        회원명 : user_9
        회원명 : user_8
        회원명 : user_7
        회원명 : user_6

        3번 째 페이지
        페이지 결과 =====
        조회된 회원 수 : 5
        회원명 : user_5
        회원명 : user_4
        회원명 : user_3
        회원명 : user_2
        회원명 : user_1
         */

    }

}
