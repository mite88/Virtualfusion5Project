import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import model.s03.Member;
import model.s03.Order;

public class AssociationTests {

    private static final EntityManagerFactory emf
            = Persistence.createEntityManagerFactory("hibernate-exp-6");

    void main() {
        many_to_one_test();
    }

    void many_to_one_test() {

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        Member member;

        try {

            tx.begin();

            member = new Member("노시한", "hwa", "chicken@hwa.com");

            em.persist(member);

            tx.commit();

        } catch ( Exception e ) {
            tx.rollback();
            throw new RuntimeException("테스트에 실패했습니다", e);
        } finally {
            em.close();
        }

        EntityManager em2 = emf.createEntityManager();
        EntityTransaction tx2 = em2.getTransaction();

        Long orderId;

        try {

            tx2.begin();

            Order order = new Order(
                    em2.find(Member.class, member.getId())
            );

            em2.persist(order);
            tx2.commit();
            orderId = order.getId();

        } catch ( Exception e ) {
            tx2.rollback();
            throw new RuntimeException(e);
        } finally {
            em2.close();
        }


        EntityManager em3 = emf.createEntityManager();
        EntityTransaction tx3 = em3.getTransaction();


        try {

            tx3.begin();

            Order order = em3.find(Order.class, orderId);
            System.out.println("주문 ID = " + order.getId());
            System.out.println("주문한 회원 이름 = " + order.getMember().getName());

            tx3.commit();

        } catch ( Exception e ) {
            tx3.rollback();
            throw new RuntimeException(e);
        } finally {
            em3.close();
        }

        emf.close();

    }

}
