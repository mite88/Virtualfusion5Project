import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import model.s02.Post;

public class EntityManagerTest1 {

    private static final EntityManagerFactory emf;

    static {
        emf = Persistence
                .createEntityManagerFactory("hibernate-exp-4");
    }

    void main() {

        // Persistence Context
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();


        try {

            tx.begin();

            // Transient
            Post post = new Post(1, "title", "contents");

            // Managed -> Insert Into
            em.persist(post);

            tx.commit();

        } catch ( Exception e ) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();


    }

}
