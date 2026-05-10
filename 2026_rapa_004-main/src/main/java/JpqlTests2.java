import jakarta.persistence.*;
import model.s03.Member;
import model.s03.Order;
import model.s03.OrderItem;
import model.s03.Product;

import java.util.List;

public class JpqlTests2 {

    static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("hibernate-exp-6");

    void main() {

        setup();

//        association_path_test();
//        distinct_test();
        findByAssociation();

        tearDown();

    }

    void setup() {

        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction tx = entityManager.getTransaction();

        try {

            tx.begin();

            // 회원 가입
            Member m1 = new Member("오지원", "pass1", "ojiwon@chicken.com");
            Member m2 = new Member("베라자", "pass1", "beraja@chicken.com");

            // 상품 등록
            Product p1 = new Product("나무배트", 150000, 50);
            Product p2 = new Product("인조 가죽 글러브", 50000, 10);

            // 회원1 주문
            Order o1 = new Order(m1);
            OrderItem oi1 = new OrderItem(p1, 1);
            OrderItem oi2 = new OrderItem(p2, 1);

            o1.addOrderItem(oi1);
            o1.addOrderItem(oi2);

            // 회원1 주문2
            Order o2 = new Order(m1);
            OrderItem oi3 = new OrderItem(p2, 3);
            o2.addOrderItem(oi3);

            entityManager.persist(m1);
            entityManager.persist(m2);

            entityManager.persist(p1);
            entityManager.persist(p2);

            entityManager.persist(o1);
            entityManager.persist(o2);

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

    void association_path_test() {

        EntityManager entityManager = emf.createEntityManager();

        String jpql = """
        select
            m
        from
            Member m
        where
            m.orderList is not empty
        """;

        List<Member> members = entityManager.createQuery(jpql, Member.class).getResultList();

        System.out.println("최소 한개 이상의 주문을 가진 회원");
        members.forEach( m -> System.out.println("회원 이름 : " + m.getName() + " 주문 갯수 : " + m.getOrderList().size()));

        entityManager.close();

    }

    void distinct_test() {

        EntityManager em = emf.createEntityManager();

        String jpql = "select distinct m from Member m left join m.orderList o";

        List<Member> members = em.createQuery(jpql, Member.class).getResultList();

        System.out.println("중복 없는 회원 조회(DISTINCT)");
        members.forEach(m -> System.out.println(m.getName()));

        em.close();

    }

    void findByAssociation() {

        EntityManager em = emf.createEntityManager();

        // jpql에서도 객체 그래프 탐사가 가능
        String jpql = "select o from Order o where o.member.name = :name";

        TypedQuery<Order> query = em.createQuery(jpql, Order.class);

        query.setParameter("name", "오지원");


        List<Order> orders = query.getResultList();
        orders.forEach(
                o -> System.out.println("주문 번호 : " + o.getId() + " 상품 갯수 : " + o.getItems().size())
        );

        em.close();

    }

}
