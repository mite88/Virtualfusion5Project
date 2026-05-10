import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import model.s01.Member;

import java.util.Objects;

public class EntityManagerTest2 {

    private static final EntityManagerFactory emf;

    static {

        emf = Persistence
                .createEntityManagerFactory("hibernate-exp-5");

    }

    void main() {

        String targetName = "user1";
        Long targetId = 1L;

        saveMember(targetName);

        Member member = findMember(targetId);
        String findMemberName = member.getName();

        System.out.println("findMemberName = " + findMemberName);
        // 검증
        System.out.println("targetName.equals(findMemberName) = " + targetName.equals(findMemberName));
        System.out.println("member.getId() == targetId = " + (Objects.equals(member.getId(), targetId)));

        String changedName = "user10";

        updateMember(targetId, changedName);
        Member member2 = findMember(targetId);

        System.out.println("member2.getName() = " + member2.getName());
        // 검증
        System.out.println("member2.getName().equals(changedName) = " + member2.getName().equals(changedName));

        deleteMember(targetId);
        Member member3 = findMember(targetId);

        System.out.println("member3 == null = " + (member3 == null));

    }

    private static void saveMember(String targetName) {
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            entityManager.persist(new Member(targetName));
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            entityManager.close();
        }
    }

    private static Member findMember(Long id) {
        EntityManager entityManager = emf.createEntityManager();
        try {
            return entityManager.find(Member.class, id);
        } finally {
            entityManager.close();
        }
    }

    private static void updateMember(Long id, String newName) {
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();

            Member findMember = entityManager.find(Member.class, id);

            if ( findMember != null ) {
                findMember.setName(newName);
            }

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            entityManager.close();
        }
    }

    private static void deleteMember(Long id) {
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction tx = entityManager.getTransaction();
        try {

            tx.begin();

            // Managed
            Member findMember = entityManager.find(Member.class, id);

            if ( findMember != null )
                entityManager.remove(findMember);

            tx.commit();

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            entityManager.close();
        }
    }

}
