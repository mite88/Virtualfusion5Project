import jakarta.persistence.*;
import model.s03.Member;

import java.util.List;

public class JpqlTests1 {

    void main() {

        final EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("hibernate-exp-6");

        setup(emf);

//        jpql_basic_select_test(emf);
//        jpql_where_condition_test(emf);
//        jpql_named_parameter_test(emf);
        jpql_position_parameter_test(emf);

        tearDown(emf);

    }

    void setup(EntityManagerFactory emf) {

        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction tx = entityManager.getTransaction();

        try {

            tx.begin();

            Member m1 = new Member("오지원", "pass1", "ojiwon@chicken.com");
            Member m2 = new Member("베라자", "pass1", "beraja@chicken.com");
            Member m3 = new Member("문현민", "pass1", "mhm@chicken.com");

            entityManager.persist(m1);
            entityManager.persist(m2);
            entityManager.persist(m3);

            tx.commit();

        } catch ( Exception e ) {
            tx.rollback();
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }

    }

    void tearDown(EntityManagerFactory emf) {
        emf.close();
    }

    void jpql_basic_select_test(EntityManagerFactory emf) {

        EntityManager entityManager = emf.createEntityManager();

        String jpql = "SELECT m FROM Member m";

        Query query = entityManager.createQuery(jpql, Member.class);

        List<Member> members = query.getResultList();
        System.out.println("조회된 회원 수 : " + members.size());

        members.forEach( m -> System.out.println("회원 이름 : " + m.getName()));

        entityManager.close();

    }

    void jpql_where_condition_test(EntityManagerFactory emf) {

        EntityManager entityManager = emf.createEntityManager();

        String jpql = """
        select
            m
        from
            Member m
        where
            m.email = 'ojiwon@chicken.com'
        """;

        Member findMember = entityManager.createQuery(jpql, Member.class)
                .getSingleResult();

        System.out.println("findMember.getName() = " + findMember.getName());

        entityManager.close();

    }

    void jpql_named_parameter_test(EntityManagerFactory emf) {

        EntityManager entityManager = emf.createEntityManager();

        String jpql = """
        select
            m
        from
            Member m
        where
            m.email = :email
        """;

        TypedQuery<Member> query = entityManager.createQuery(jpql, Member.class);
        query.setParameter("email", "mhm@chicken.com");

        Member findMember = query.getSingleResult();
        System.out.println("findMember.getName() = " + findMember.getName());

        entityManager.close();

    }

    void jpql_position_parameter_test(EntityManagerFactory emf) {

        EntityManager entityManager = emf.createEntityManager();

        /*
        select
            m1_0.id,
            m1_0.balance,
            m1_0.email,
            m1_0.lastLoginAt,
            m1_0.name,
            m1_0.password,
            m1_0.signupAt
        from
            members m1_0
        where
            m1_0.email=?
         */

        String jpql = """
        select
            m
        from
            Member m
        where
            m.email = ?1
        """;

        TypedQuery<Member> query = entityManager.createQuery(jpql, Member.class);
        query.setParameter(1, "mhm@chicken.com");


        Member findMember = query.getSingleResult();
        System.out.println("findMember.getName() = " + findMember.getName());

        entityManager.close();

    }

}
