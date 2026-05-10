import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import model.s03.Member;

public class PersistenceContext1 {

    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("hibernate-exp-6");

    void main() {
        first_lv_cache();
        emf.close();
    }

    void first_lv_cache() {

        String name = "누헨진";
        String password = "hwa";
        String email = "nhj@chicken.com";

        Member savedMember = saveMember(name, password, email);

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {

            System.out.println("첫번째 조회 시도");
            Member findMember1 = em.find(Member.class, savedMember.getId());
            System.out.println("찾아온 회원 이름 : " + findMember1.getName());

            System.out.println("두번째 조회 시도");
            Member findMember2 = em.find(Member.class, savedMember.getId());
            System.out.println("찾아온 회원 이름 : " + findMember2.getName());

            System.out.println("findMember1 == findMember2 :");
            System.out.println(findMember1 == findMember2);

        } catch (Exception e ) {
            tx.rollback();
            throw new RuntimeException(e);
        } finally {
            em.close();
        }


        EntityManager em2 = emf.createEntityManager();
        EntityTransaction tx2 = em2.getTransaction();

        try {

            System.out.println("세번째 조회 시도");
            Member findMember1 = em2.find(Member.class, savedMember.getId());
            System.out.println("찾아온 회원 이름 : " + findMember1.getName());

            System.out.println("네번째 조회 시도");
            Member findMember2 = em2.find(Member.class, savedMember.getId());
            System.out.println("찾아온 회원 이름 : " + findMember2.getName());

            System.out.println("(2) findMember1 == findMember2 :");
            System.out.println(findMember1 == findMember2);

        } catch (Exception e ) {
            tx2.rollback();
            throw new RuntimeException(e);
        } finally {
            em2.close();
        }




    }

    Member saveMember(String name, String password, String email) {
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction tx = entityManager.getTransaction();

        try {

            tx.begin();

            Member member = new Member(name, password, email);
            entityManager.persist(member);

            tx.commit();

            return member;

        } catch ( Exception e ) {
            tx.rollback();
            throw new RuntimeException(e);
        } finally {
            entityManager.close();
        }

    }


}
